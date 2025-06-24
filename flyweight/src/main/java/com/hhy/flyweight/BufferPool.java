package com.hhy.flyweight;

import java.nio.ByteBuffer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class BufferPool {
    /**
     * 槽大小
     */
    private final int slotSize;
    /**
     * 总大小
     */
    private final int totalSize;
    /**
     * 可用大小
     */
    private int availableSize;

    private final Deque<ByteBuffer> slotQueue = new ArrayDeque<>();
    private final Deque<Condition> waiters = new ArrayDeque<>();
    private final Lock lock = new ReentrantLock();

    public BufferPool(int slotSize, int totalSize) {
        this.slotSize = slotSize;
        this.totalSize = totalSize;
        this.availableSize = totalSize;
    }

    public ByteBuffer allocate(int size, long timeout) throws InterruptedException {
        if (totalSize < size || size <= 0) {
            throw new RuntimeException("申请容量非法");
        }
        lock.lock();
        try {
            if (size == slotSize && !slotQueue.isEmpty()) {
                return slotQueue.pollFirst();
            }
            if ((availableSize + slotQueue.size() * slotSize) >= size) {
                freeUp(size);
                availableSize -= size;
                return ByteBuffer.allocate(size);
            }
            // 等待内存
            Condition condition = lock.newCondition();
            waiters.addLast(condition);
            long remainTime = timeout;
            try {
                while (true) {
                    long startTime = System.currentTimeMillis();
                    boolean wakeup = condition.await(remainTime, TimeUnit.MILLISECONDS);
                    if (!wakeup) {
                        throw new RuntimeException("规定时间内无法申请所需内存");
                    }
                    if (size == slotSize && !slotQueue.isEmpty()) {
                        return slotQueue.pollFirst();
                    }
                    if ((availableSize + slotQueue.size() * slotSize) >= size) {
                        freeUp(size);
                        availableSize -= size;
                        return ByteBuffer.allocate(size);
                    }
                    remainTime -= (System.currentTimeMillis() - startTime);
                }
            } finally {
                waiters.remove(condition);
            }

        } finally {
            if (!waiters.isEmpty() && !(availableSize == 0 && slotQueue.isEmpty())) {
                waiters.peekFirst().signal();
            }
            lock.unlock();
        }

    }

    private void freeUp(int size) {
        while (availableSize < size && !slotQueue.isEmpty()) {
            availableSize += slotQueue.pollFirst().capacity();
        }
    }

    public void deallocate(ByteBuffer buffer) {
        lock.lock();
        try {
            int capacity = buffer.capacity();
            if (capacity == slotSize) {
                slotQueue.addLast(buffer);
            } else {
                availableSize += buffer.capacity();
            }
            if (!waiters.isEmpty()) {
                waiters.peekFirst().signal();
            }
            buffer = null;
        } finally {
            lock.unlock();
        }
    }
}

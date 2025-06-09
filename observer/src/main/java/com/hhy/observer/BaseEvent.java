package com.hhy.observer;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public abstract class BaseEvent implements Event {
    private long timestamp;
    protected BaseEvent() {
        timestamp = System.currentTimeMillis();
    }

    @Override
    public long timestamp() {
        return timestamp;
    }
}

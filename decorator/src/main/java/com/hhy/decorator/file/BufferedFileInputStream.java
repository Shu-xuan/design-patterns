package com.hhy.decorator.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class BufferedFileInputStream extends InputStream {
    private final byte[] buffer = new byte[8192];
    /**
     * 读取到
     */
    private int position = -1;
    /**
     * 缓冲的有效长度
     */
    private int capacity = -1;
    private final FileInputStream fileInputStream;

    public BufferedFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    @Override
    public int read() {
        if (bufferCanRead()) {
            return readFromBuffer();
        }
        refreshBuffer();
        if (!bufferCanRead()) {
            return -1;
        }
        return readFromBuffer();
    }

    private void refreshBuffer() {
        try {
            capacity = fileInputStream.read(buffer);
            position = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int readFromBuffer() {
        return buffer[position++] & 0xFF;
    }

    private boolean bufferCanRead() {
        if (capacity == -1) {
            return false;
        }
        if (position == capacity) {
            return false;
        }
        return true;
    }
}

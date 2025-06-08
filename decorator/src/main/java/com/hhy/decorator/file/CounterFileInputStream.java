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
public class CounterFileInputStream extends InputStream {
    private int readCount = 0;
    private final BufferedFileInputStream bufferedFileInputStream;

    public CounterFileInputStream(BufferedFileInputStream bufferedFileInputStream) {
        this.bufferedFileInputStream = bufferedFileInputStream;
    }

    @Override
    public int read() {
        int read = bufferedFileInputStream.read();
        if (read == -1) {
            System.out.println("总共读取了: " + readCount + " 次");
        } else {
            increment();
        }
        return read;
    }

    private void increment() {
        readCount++;
    }

}

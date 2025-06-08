package com.hhy.count;

import com.hhy.facade.plugin.MyPlugin;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class CountPlugin implements MyPlugin {
    AtomicInteger count = new AtomicInteger(0);
    @Override
    public void beforeGetTime() {
        System.out.println(count.incrementAndGet());
    }
}

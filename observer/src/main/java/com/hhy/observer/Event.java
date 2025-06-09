package com.hhy.observer;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public interface Event {
    long timestamp();
    Object source();
}

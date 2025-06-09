package com.hhy.observer;

import java.util.function.Consumer;

/**
 * <p>
 * 描述: 相当于事件监听者
 * </p>
 *
 * @Author huhongyuan
 */
public class User implements EventListener{
    private final String name;
    private final Consumer<String> consumer;

    public User(String name, Consumer<String> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    public void receiveInfo(String string) {
        consumer.accept(string);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof WeatherUpdateEvent) {
            receiveInfo(event.source().toString());
        }
    }
}

package com.hhy.observer;

/**
 * <p>
 * 描述: 天气事件
 * </p>
 *
 * @Author huhongyuan
 */
public class WeatherUpdateEvent extends BaseEvent {
    private final String info;

    public WeatherUpdateEvent(String info) {
        this.info = info;
    }

    @Override
    public Object source() {
        return info;
    }
}

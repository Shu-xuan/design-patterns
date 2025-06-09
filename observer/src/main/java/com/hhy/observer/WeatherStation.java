package com.hhy.observer;

import java.util.Random;

/**
 * <p>
 * 描述: 相当于事件发布者
 * </p>
 *
 * @Author huhongyuan
 */
public class WeatherStation {
    private final TVStation tvStation;

    public WeatherStation(TVStation tvStation) {
        this.tvStation = tvStation;
    }

    private String getInfo() {
        if (new Random(2014563320).nextBoolean()) {
            return "晴天";
        }
        return "雨天";
    }

    public void publish() {
        while (true) {
            String info = getInfo();
            tvStation.publish(new WeatherUpdateEvent(info));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        }
    }
}

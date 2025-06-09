package com.hhy.observer;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static void main(String[] args) {
        TVStation tvStation = new TVStation();
        WeatherStation weatherStation = new WeatherStation(tvStation);
        User tom = new User("Tom", weather -> {
            if ("晴天".equals(weather)) {
                System.out.println("晴天Tom出门");
            } else {
                System.out.println("Tom呆家里");
            }
        });
        User jerry = new User("Tom", weather -> {
            if ("晴天".equals(weather)) {
                System.out.println("晴天Jerry呆家里");
            } else {
                System.out.println("Jerry打洞");
            }
        });
        tvStation.subscribe(tom, WeatherUpdateEvent.class);
        tvStation.subscribe(jerry, WeatherUpdateEvent.class);
        weatherStation.publish();
    }
}

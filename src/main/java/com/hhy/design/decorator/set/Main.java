package com.hhy.design.decorator.set;

import java.util.HashSet;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static void main(String[] args) {
        HistorySet<String> historySet = new HistorySet<>(new HashSet<>());
        HistorySet<String> set = new HistorySet<>(historySet);
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.remove("4");
        set.remove("4");
        set.remove("5");
        set.remove("1");
        System.out.println(set);
    }
}

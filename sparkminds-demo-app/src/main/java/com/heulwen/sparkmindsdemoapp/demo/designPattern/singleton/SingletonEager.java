package com.heulwen.sparkmindsdemoapp.demo.designPattern.singleton;

public class SingletonEager {
    private static final SingletonEager instance = new SingletonEager();

    private SingletonEager() {

    }

    public static SingletonEager getInstance() {
        // Cần là có ngay, không phải đợi
        return instance;
    }
}

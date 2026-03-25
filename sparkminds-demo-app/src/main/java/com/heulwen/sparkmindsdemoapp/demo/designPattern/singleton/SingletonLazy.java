package com.heulwen.sparkmindsdemoapp.demo.designPattern.singleton;

public class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy() {
        // Ngăn chặn khởi tạo từ bên ngoài
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            // Nếu chưa có thì mới bắt đầu khởi tạo
            instance = new SingletonLazy();
        }
        return instance;
    }
}

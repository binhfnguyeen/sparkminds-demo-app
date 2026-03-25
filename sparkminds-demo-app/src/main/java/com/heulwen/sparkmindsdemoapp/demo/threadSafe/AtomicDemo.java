package com.heulwen.sparkmindsdemoapp.demo.threadSafe;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger x = new AtomicInteger(10);
        x.compareAndSet(15, 20);

        System.out.println(x.get());
    }
}

package com.heulwen.sparkmindsdemoapp.demo.OOP;

// Interface
public interface Refundable {
    // Mọi biến trong Interface mặc định là public static final
    int MAX_REFUND_DAYS = 30;

    // Mọi hàm mặc định là public abstract
    boolean refund(double refundAmount);

    default String hello(String name) {
        return String.format("Hello, %s!", name);
    }
}

package com.heulwen.sparkmindsdemoapp.demo.designPattern.proxy;

// Real Object
public class VNPayService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán qua VNPay: " + amount);
    }
}

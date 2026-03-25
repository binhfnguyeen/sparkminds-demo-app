package com.heulwen.sparkmindsdemoapp.demo.designPattern.proxy;

// Proxy
public class PaymentProxy implements PaymentService {
    private PaymentService realService;

    public PaymentProxy(PaymentService realService) {
        this.realService = realService;
    }

    @Override
    public void pay(double amount) {
        // Authorization
        if (amount > 1000000) {
            System.out.println("Cần xác thực thêm (OTP)!");
        }

        // Logging
        System.out.println("Log: Chuẩn bị thanh toán " + amount);

        // Gọi Real Object
        realService.pay(amount);

        // Logging
        System.out.println("Log: Thanh toán xong");
    }
}

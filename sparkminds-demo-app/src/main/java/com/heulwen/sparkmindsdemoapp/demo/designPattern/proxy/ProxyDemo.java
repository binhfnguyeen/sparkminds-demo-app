package com.heulwen.sparkmindsdemoapp.demo.designPattern.proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        PaymentService real = new VNPayService();

        // Dùng Proxy thay vì gọi trực tiếp
        PaymentService proxy = new PaymentProxy(real);

        proxy.pay(500000);
        proxy.pay(2000000);
    }
}

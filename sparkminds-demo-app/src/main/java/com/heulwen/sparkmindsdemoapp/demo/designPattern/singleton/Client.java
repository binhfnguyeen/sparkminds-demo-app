package com.heulwen.sparkmindsdemoapp.demo.designPattern.singleton;

public class Client {
    public static void main(String[] args) {
        // Developer A gọi ở màn hình Login
        SingletonEager s1 = SingletonEager.getInstance();

        // Developer B gọi ở màn hình Thanh toán
        SingletonEager s2 = SingletonEager.getInstance();

        // KIỂM CHỨNG: Liệu hai người có đang dùng chung một thứ?
        if (s1 == s2) {
            System.out.println("Thành công! Cả hai đều là cùng một object duy nhất.");
        } else {
            System.out.println("Thất bại! Có hai object khác nhau tồn tại.");
        }
    }
}

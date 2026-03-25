package com.heulwen.sparkmindsdemoapp.demo.string;

public class StringDemo {
    public static void main(String[] args) {
        String s1 = "Java";
        String s2 = "Java";
        // Trình biên dịch từ gộp "Ja" + "va" thành "Java" ngay lúc compile
        String s3 = "Ja" + "va";

        // Cả 3 biến đều trỏ chung vào đúng MỘT vùng nhớ trong String Pool
        System.out.println("s1 == s2: " + (s1 == s2)); // true
        System.out.println("s1 == s3: " + (s1 == s3)); // true

        // Khi ta cố gắng thay đổi s1, nó tạo ra object MỚI, s2 và s3 không bị ảnh hưởng
        s1 = s1.toUpperCase();
        System.out.println("s1 sau khi đổi: " + s1); // JAVA
        System.out.println("s2 hiện tại: " + s2);   // Java
    }
}

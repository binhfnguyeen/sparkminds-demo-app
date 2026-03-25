package com.heulwen.sparkmindsdemoapp.demo.java8;

import java.util.Optional;

public class OptionalPeformanceTrap {
    public static void main(String[] args) {
        // Giả sử tìm thấy User trong Cache (Dữ liệu KHÔNG null)
        Optional<String> userFromCache = Optional.of("User_BinhNguyen");

        System.out.println("--- Test 1: Dùng orElse (LỖI LOGIC HIỆU NĂNG) ---");
        // Dù userFromCache có dữ liệu, hàm callDatabase() VẪN BỊ CHẠY!
        String result1 = userFromCache.orElse(callDatabase());
        System.out.println("Kết quả 1: " + result1);

        System.out.println("\n--- Test 2: Dùng orElseGet ---");
        // Hàm callDatabase() SẼ KHÔNG BỊ CHẠY, tiết kiệm tài nguyên tuyệt đối.
        String result2 = userFromCache.orElseGet(() -> callDatabase());
        System.out.println("Kết quả 2: " + result2);
    }
    // Mô phỏng một tác vụ cực kỳ tốn tài nguyên
    private static String callDatabase(){
        System.out.println("[CẢNH BÁO] Đang kết nối Database... Tốn 2 giây CPU!");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        return "User_From_DB";
    }
}

package com.heulwen.sparkmindsdemoapp.demo.string;

public class StringBuilderDemo {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Kỹ thuật Advanced: Biết trước cần tạo chuỗi dài 100.000 ký tự
        // Cấp phát luôn sức chứa 100.000 để KHÔNG BAO GIỜ bị cấp phát lại (Reallocation)
        StringBuilder sb = new StringBuilder(100000);

        for (int i = 0; i < 100000; i++) {
            // Ghi trực tiếp vào mảng nội bộ, không tạo ra object String mới nào
            sb.append("A");
        }

        String result = sb.toString(); // Chỉ tạo ra 1 object String duy nhất ở bước cuối
        long endTime = System.currentTimeMillis();

        System.out.println("Độ dài: " + result.length());
        System.out.println("Thời gian thực thi: " + (endTime - startTime) + " ms");
    }
}

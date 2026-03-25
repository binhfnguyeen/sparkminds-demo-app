package com.heulwen.sparkmindsdemoapp.demo.threadSafe.DateNCalendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModernDateDemo {
    // Hoàn toàn an toàn khi để static final và chia sẻ cho hàng vạn luồng
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            int threadNum = i;
            executor.submit(() -> {
                String dateString = "2023-01-0" + (threadNum % 9 + 1);

                // Parse ra LocalDate. Đối tượng trả về là bất biến.
                LocalDate parsedDate = LocalDate.parse(dateString, FORMATTER);

                System.out.println("Thread " + threadNum + " parsed flawlessly: " + parsedDate);
            });
        }
        executor.shutdown();
    }
}

package com.heulwen.sparkmindsdemoapp.demo.threadSafe.DateNCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrokenDateDemo {
    // Dùng chung một instance có state (trạng thái) thay đổi được
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            int threadNum = i;
            executor.submit(() -> {
                try {
                    // Các luồng tranh giành nhau thay đổi biến 'calendar' bên trong SDF
                    String dateString = "2023-01-0" + (threadNum % 9 + 1);
                    Date parsedDate = SDF.parse(dateString);
                    System.out.println("Thread " + threadNum + " parsed: " + parsedDate);
                } catch (ParseException | NumberFormatException e) {
                    // Rất dễ văng lỗi NumberFormatException hoặc ArrayIndexOutOfBoundsException
                    System.err.println("Thread " + threadNum + " CRASHED: " + e.getMessage());
                }
            });
        }
        executor.shutdown();
    }
}

package com.heulwen.sparkmindsdemoapp.demo.threadSafe.DateNCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDateDemo {

    // Khởi tạo ThreadLocal: Mỗi Thread khi gọi .get() sẽ nhận được 1 SimpleDateFormat của riêng nó
    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL_SDF =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            int threadNum = i;
            executor.submit(() -> {
                try {
                    String dateString = "2023-01-0" + (threadNum % 9 + 1);
                    // Lấy ra formatter CỦA RIÊNG LUỒNG NÀY
                    SimpleDateFormat sdf = THREAD_LOCAL_SDF.get();
                    Date parsedDate = sdf.parse(dateString);
                    System.out.println("Thread " + threadNum + " parsed safely: " + parsedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    // Tránh rò rỉ bộ nhớ (Memory Leak) trong Thread Pool
                    THREAD_LOCAL_SDF.remove();
                }
            });
        }
        executor.shutdown();
    }
}

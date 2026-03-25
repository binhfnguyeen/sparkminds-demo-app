package com.heulwen.sparkmindsdemoapp.demo.threadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadSafeBugDemo {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        // Thêm dữ liệu ban đầu
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        // Thread 1: đọc phần tử cuối
        Thread t1 = new Thread(() -> {
            try {
                int lastIndex = list.size() - 1;

                // Giả lập delay để thread khác chen vào
                Thread.sleep(100);

                Integer value = list.get(lastIndex);
                System.out.println("Last value: " + value);
            } catch (Exception e) {
                System.out.println("Thread 1 ERROR: " + e);
            }
        });

        /*
         * Thread t1 = new Thread(() -> {
         *    try {
         *        synchronized (list) {
         *           int lastIndex = list.size() - 1;
         *           Thread.sleep(100);
         *           Integer value = list.get(lastIndex);
         *           System.out.println("Last value: " + value);
         *        }
         *    } catch (Exception e) {
         *        System.out.println("Thread 1 ERROR: " + e);
         *    }
         * });
         * */

        // Thread 2: xóa phần tử cuối
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(50);
                list.remove(list.size() - 1);
                System.out.println("Removed last element");
            } catch (Exception e) {
                System.out.println("Thread 2 ERROR: " + e);
            }
        });

        t1.start();
        t2.start();
    }
}

package com.heulwen.sparkmindsdemoapp.demo.threadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedDemo {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        list.add("A"); // modCount = 1
        list.add("B"); // modCount = 2
        list.add("C"); // modCount = 3

        // Luồng 1: Cố gắng duyệt danh sách
        Runnable readTask = () -> {
            // LỖI: Dù là synchronizedList, Iterator của nó KHÔNG thread-safe
            // Phải bọc trong block: synchronized(list) { ... } mới an toàn
            for (String s : list) { // Khi tạo Iterator --> Lưu lại expectedModCount
                System.out.println("Đọc: " + s); // So sánh modCount hiện tại với expectedModCount
                // modCount != expectedModCount => throw ConcurrentModificationException
                try { Thread.sleep(50); } catch (Exception e){ System.out.println(e.getMessage()); }
            }
        };

        // Luồng 2: Cố gắng xóa phần tử
        Runnable writeTask = () -> {
            try { Thread.sleep(20); } catch (Exception e){ System.out.println(e.getMessage()); }
            list.remove("B"); // modCount = 4
            System.out.println("Đã xóa B");
        };
        new Thread(readTask).start();
        new Thread(writeTask).start();
        // Kết quả: Xác suất rất cao ném ra java.util.ConcurrentModificationException
    }
}


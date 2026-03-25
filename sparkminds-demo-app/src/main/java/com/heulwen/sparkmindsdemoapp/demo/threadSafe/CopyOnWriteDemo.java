package com.heulwen.sparkmindsdemoapp.demo.threadSafe;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("A"); list.add("B"); list.add("C");

        Runnable readTask = () -> {
            // KHÔNG CẦN synchronized.
            // Iterator này thao tác trên "snapshot" của mảng lúc bắt đầu vòng lặp
            for (String s : list) {
                System.out.println("Đọc: " + s);
                try { Thread.sleep(50); } catch (Exception e){}
            }
        };

        Runnable writeTask = () -> {
            try { Thread.sleep(20); } catch (Exception e){}
            list.remove("B"); // Tạo ra mảng mới [A, C], không ảnh hưởng snapshot của luồng đọc
            System.out.println("Đã xóa B");
        };
        new Thread(readTask).start();
        new Thread(writeTask).start();

        /* Kết quả:
           Đọc: A
           Đã xóa B
           Đọc: B  <-- Luồng đọc vẫn thấy B vì nó đang đọc snapshot cũ
           Đọc: C
           Không có Exception nào xảy ra!
        */
    }
}

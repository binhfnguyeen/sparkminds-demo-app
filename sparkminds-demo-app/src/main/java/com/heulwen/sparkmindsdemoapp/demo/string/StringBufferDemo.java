package com.heulwen.sparkmindsdemoapp.demo.string;

public class StringBufferDemo {
    public static void main(String[] args) throws InterruptedException {
        // Dùng StringBuffer để an toàn luồng
        StringBuffer sharedBuffer = new StringBuffer();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                // Phương thức append() này có từ khóa 'synchronized' nội bộ.
                // Khi Thread 1 đang viết, Thread 2 bị Block (đứng chờ).
                sharedBuffer.append("A");
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start(); t2.start();
        t1.join(); t2.join();

        // Luôn luôn ra đúng 2000.
        // Nếu thay bằng StringBuilder, kết quả sẽ bị hao hụt (VD: 1985) do Race Condition.
        System.out.println("Độ dài StringBuffer: " + sharedBuffer.length());
    }
}

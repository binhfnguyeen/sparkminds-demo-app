package com.heulwen.sparkmindsdemoapp.demo.threadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        // Dùng ConcurrentHashMap thay vì Collections.synchronizedMap(new HashMap<>())
        Map<String, Integer> map = new ConcurrentHashMap<>();

        // Tạo thread pool với 10 luồng
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 10 luồng cùng thi nhau tăng giá trị của key "Counter" lên 1000 lần
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    // compute() là thao tác atomic thread-safe của CHM
                    // Nó chỉ lock đúng cái bucket chứa "Counter", các bucket khác vẫn tự do
                    map.compute("Counter", (key, val) -> (val == null) ? 1 : val + 1);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Kết quả luôn luôn chính xác là 10000.
        // Nếu dùng HashMap thường, kết quả sẽ sai lệch và nhỏ hơn 10000 do Race Condition.
        System.out.println("Kết quả đếm: " + map.get("Counter"));
    }
}

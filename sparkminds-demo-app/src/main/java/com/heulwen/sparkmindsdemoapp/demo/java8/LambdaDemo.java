package com.heulwen.sparkmindsdemoapp.demo.java8;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface TaskProcessor { void process(String taskName); }
public class LambdaDemo {
    public static void main(String[] args) {
        List<String> tasks = Arrays.asList("Build Docker", "Deploy K8s", "Run Tests");
        // --- Giai đoạn 1: Kiểu cũ (Anonymous Class) ---
        // Sinh ra file .class phụ, tốn bộ nhớ tĩnh
        TaskProcessor oldWay = new TaskProcessor() {
            @Override
            public void process(String task) {
                System.out.println("Executing: " + task);
            }
        };
        // --- Giai đoạn 2: Lambda Expression ---
        // Dùng invokedynamic, tối ưu bộ nhớ, cú pháp sạch
        TaskProcessor lambdaWay = task -> System.out.println("Executing: " + task);
        // --- Giai đoạn 3: Method Reference ---
        // Tái sử dụng một hàm đã có sẵn trên bộ nhớ tĩnh (Static Method)
        // Method reference không tạo ra class kế thừa hay mở rộng interface, mà nó chỉ cung cấp
        // một implementation cho functional interface bằng cách ánh xạ method có cùng signature.
        TaskProcessor methodRefWay = LambdaDemo::executeTask;
        // Chạy thử với Method Reference
        tasks.forEach(methodRefWay::process);
    }
    // Một hàm tĩnh có chung "chữ ký" (nhận 1 String, trả về void) với TaskProcessor
    private static void executeTask(String task){
        System.out.println("System Executing: " + task);
    }
}

package com.heulwen.sparkmindsdemoapp.demo.java8;

interface DatabaseLogger{
    default void log(String msg){
        System.out.println("Lưu log vào Databse: " + msg);
    }
}

interface FileLogger {
    default void log(String msg){
        System.out.println("Ghi log ra File TXT: " + msg);
    }
}

// Lỗi Compile sẽ xảy ra ở đây nếu không Override hàm log()
public class DefaultMethodDemo implements DatabaseLogger, FileLogger{

    @Override
    public void log(String msg) {
        // Gọi Default của interface cụ thể
        DatabaseLogger.super.log(msg);
        FileLogger.super.log(msg);
        System.out.println("Hoàn tất ghi log hệ thống.");
    }

    public static void main(String[] args) {
        new DefaultMethodDemo().log("System OutOfMemory");
    }
}

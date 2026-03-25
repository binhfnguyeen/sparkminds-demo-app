package com.heulwen.sparkmindsdemoapp.demo.java8;

import java.util.Arrays;
import java.util.List;

class Server {
    String name;
    String status;
    public Server(String name, String status) { this.name = name; this.status = status; }
}

public class StreamDemo {
    public static void main(String[] args) {
        List<Server> servers = Arrays.asList(
                new Server("App-Server-1", "UP"),
                new Server("DB-Server-1", "DOWN"),
                new Server("Cache-Server-1", "DOWN"),
                new Server("App-Server-2", "DOWN"), // Cái này sẽ KHÔNG bao giờ bị duyệt tới
                new Server("MQ-Server-1", "UP")
        );
        System.out.println("Bắt đầu chạy Stream Pipeline: ");
        List<String> failedServers = servers.stream()
                .filter(srv -> {
                    System.out.println("Dang filter: " + srv.name);
                    return "DOWN".equals(srv.status);
                })
                .map(srv -> {
                    System.out.println("Dang map: " + srv.name);
                    return srv.name.toUpperCase();
                })
                // Short-circuit: Chỉ cần tìm đủ 2 cái là DỪNG TOÀN BỘ luồng
                .limit(2)
                .toList();
        System.out.println("\nKết quả: " + failedServers);


    }
}

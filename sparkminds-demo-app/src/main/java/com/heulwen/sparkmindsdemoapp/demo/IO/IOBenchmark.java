package com.heulwen.sparkmindsdemoapp.demo.IO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class IOBenchmark {

    private static final String FILE_NAME = "benchmark_50MB.dat";
    // Tạo file 50 Megabytes
    private static final int FILE_SIZE = 50 * 1024 * 1024;

    public static void main(String[] args) throws Exception {
        System.out.println("Đang tạo file dữ liệu 50MB...");
        createDummyFile();
        System.out.println("Tạo file hoàn tất! Bắt đầu Benchmark...\n");

        benchmarkFileInputStream();
        benchmarkBufferedInputStream();
        benchmarkFileChannelNIO();

        // Dọn dẹp file sau khi test
        Files.deleteIfExists(Paths.get(FILE_NAME));
    }

    // 1. Thảm họa System Call: Đọc từng byte trực tiếp từ đĩa
    private static void benchmarkFileInputStream() {
        long startTime = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream(FILE_NAME)) {
            int data;
            // Gọi hàm read() 52,428,800 lần!
            while ((data = fis.read()) != -1) {
                // Không làm gì, chỉ mô phỏng việc đọc
            }
        } catch (IOException e) { e.printStackTrace(); }
        long endTime = System.currentTimeMillis();
        System.out.println("[1] FileInputStream (Byte-by-Byte): " + (endTime - startTime) + " ms");
    }

    // 2. Nghệ thuật Wrapper: Đọc theo Block bằng RAM nội bộ
    private static void benchmarkBufferedInputStream() {
        long startTime = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FILE_NAME))) {
            byte[] buffer = new byte[8192]; // Cấp phát buffer 8KB
            int bytesRead;
            // Chỉ gọi read() khoảng 6.400 lần
            while ((bytesRead = bis.read(buffer)) != -1) {
            }
        } catch (IOException e) { e.printStackTrace(); }
        long endTime = System.currentTimeMillis();
        System.out.println("[2] BufferedInputStream (8KB Block): " + (endTime - startTime) + " ms");
    }

    // 3. Kỷ nguyên Zero-Copy: Truy xuất trực tiếp bằng FileChannel
    private static void benchmarkFileChannelNIO() {
        long startTime = System.currentTimeMillis();
        Path path = Paths.get(FILE_NAME);
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            // Cấp phát bộ nhớ Direct (Bỏ qua JVM Heap, ánh xạ thẳng vào OS Memory)
            ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
            while (channel.read(buffer) != -1) {
                buffer.clear(); // Xóa trạng thái buffer để đọc block tiếp theo
            }
        } catch (IOException e) { e.printStackTrace(); }
        long endTime = System.currentTimeMillis();
        System.out.println("[3] FileChannel NIO (Direct Buffer): " + (endTime - startTime) + " ms");
    }

    // Hàm phụ trợ: Tạo file ảo
    private static void createDummyFile() throws IOException {
        Path path = Paths.get(FILE_NAME);
        byte[] chunk = new byte[1024 * 1024]; // 1MB chunk
        try (OutputStream os = Files.newOutputStream(path, StandardOpenOption.CREATE)) {
            for (int i = 0; i < 50; i++) {
                os.write(chunk);
            }
        }
    }
}

package com.heulwen.sparkmindsdemoapp.services;

import com.heulwen.sparkmindsdemoapp.models.Student;
import java.io.*;
import java.util.List;

public interface FileHandlingService {
    // ===== TXT =====
    File exportTxt(List<Student> students) throws IOException;

    // ===== CSV =====
    File exportCsv(List<Student> students) throws Exception;

    // ===== EXCEL =====
    File exportExcel(List<Student> students) throws IOException;

    // ===== READ TXT =====
    List<Student> readTxt(InputStream inputStream) throws IOException;

    // ===== READ CSV =====
    List<Student> readCsv(InputStream inputStream) throws Exception;

    // ===== READ EXCEL =====
    List<Student> readExcel(InputStream inputStream) throws IOException;

    // Đọc nội dung file demo tương tác
    String readInteractiveFile() throws IOException;

    // Ghi file: append = true (thêm vào cuối), append = false (ghi đè tại position)
    String writeInteractiveFile(String content, Long position, boolean append) throws IOException;
}

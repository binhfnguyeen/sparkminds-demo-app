package com.heulwen.sparkmindsdemoapp.services;

import com.heulwen.sparkmindsdemoapp.models.Student;
import jakarta.annotation.PostConstruct;

import java.util.*;

public interface StudentService {
    @PostConstruct
    void init();
    // --- DEMO ARRAYLIST ---
    // ArrayList có cấu trúc mảng động, cho phép get(index) với tốc độ O(1)
    Student getByIndex(int index);
    // --- DEMO LINKEDLIST ---
    // Ép kiểu hoặc khởi tạo mới sang LinkedList để sử dụng addFirst/addLast/removeFirst/removeLast
    // LinkedList tối ưu cho việc chèn/xóa ở đầu và cuối danh sách
    void addFirst(Student s);
    void addLast(Student s);
    void removeFirst();
    void removeLast();
    List<Student> getStudents();
    void add(Student s);
    void delete(Long id);
}

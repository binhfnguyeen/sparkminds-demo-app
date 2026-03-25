package com.heulwen.sparkmindsdemoapp.demo.java8;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    Integer id;
    String name;
    Double salary;
    public Employee(Integer id, String name, Double salary) {this.id = id;this.name = name;this.salary = salary;}
}
public class Stream2Demo {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee (1, "Van A", 250.0),
                new Employee (2, "Thi B", 300.0)
        );
        // Thay cho vòng lặp for-each truyền thống
        employees.stream().forEach(emp -> System.out.println(emp.name));
        // Key là id, Value là tên
        Map<Integer, String> empMap = employees.stream()
                .collect(Collectors.toMap(
                        emp -> emp.id,
                        emp -> emp.name,
                        (oldValue, newValue) -> newValue // Nếu trùng ID, lấy tên mới
                ));
        // Cấu trúc Advanced: Dùng Comparator kết hợp Method Reference
        Optional<Employee> maxSalaryEmp = employees.stream()
                .max(Comparator.comparingDouble(emp -> emp.salary));
        Optional<Employee> minSalaryEmp = employees.stream()
                .min(Comparator.comparingDouble(emp -> emp.salary));
        // Xử lý an toàn với Optional
        maxSalaryEmp.ifPresent(emp -> System.out.println("Lương cao nhất: " + emp.name));
    }
}

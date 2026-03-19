package com.heulwen.sparkmindsdemoapp.services;

import com.heulwen.sparkmindsdemoapp.models.Student;
import com.heulwen.sparkmindsdemoapp.util.FileUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class StudentService {
    private static final String FILE_PATH = "data.txt";
    private List<Student> students;

    @PostConstruct
    public void init() {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + FILE_PATH);
            }

            students = FileUtil.readFile(FILE_PATH);

            if (students.isEmpty()) {
                students.add(new Student(1L, "Nguyen Van A", 8.5));
                students.add(new Student(2L, "Tran Thi B", 7.2));
                students.add(new Student(3L, "Le Van C", 9.0));

                FileUtil.writeFile(FILE_PATH, students);

                System.out.println("Inserted default data!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long generateId() {
        if (students.isEmpty()) return 1L;

        return students.stream()
                .mapToLong(Student::getId)
                .max()
                .orElse(0L) + 1;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void add(Student s){
        students.add(s);
        FileUtil.writeFile(FILE_PATH, students);
    }

    public void delete(Long id) {
        students.removeIf(s -> s.getId().equals(id));
        FileUtil.writeFile(FILE_PATH, students);
    }

    public void update(Student s) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(s.getId())) {
                students.set(i, s);
                break;
            }
        }
        FileUtil.writeFile(FILE_PATH, students);
    }
}

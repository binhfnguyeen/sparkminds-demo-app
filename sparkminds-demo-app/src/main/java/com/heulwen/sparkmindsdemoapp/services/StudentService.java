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
        Long newId = generateId();
        s.setId(newId);

        students.add(s);
        FileUtil.writeFile(FILE_PATH, students);
    }

    public Student findById(Long id){
        Map<Long, Student> map = new HashMap<>();
        for (Student s: students){
            map.put(s.getId(), s);
        }
        return map.get(id);
    }

    public List<Student> sortByScore(){
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getScore))
                .toList();
    }
}

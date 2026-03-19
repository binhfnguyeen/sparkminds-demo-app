package com.heulwen.sparkmindsdemoapp.services.impl;

import com.heulwen.sparkmindsdemoapp.models.Student;
import com.heulwen.sparkmindsdemoapp.services.StudentService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static final String FILE_PATH = "data.txt";
    private List<Student> students;

    @Override
    @PostConstruct
    public void init() {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + FILE_PATH);
            }

            students = readFile(FILE_PATH);

            if (students.isEmpty()) {
                students.add(new Student(1L, "Nguyen Van A", 8.5));
                students.add(new Student(2L, "Tran Thi B", 7.2));
                students.add(new Student(3L, "Le Van C", 9.0));

                writeFile(FILE_PATH, students);

                System.out.println("Inserted default data!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getByIndex(int index) {
        if (index >= 0 && index < students.size()) {
            return students.get(index);
        }
        return null;
    }

    @Override
    public void addFirst(Student s) {
        LinkedList<Student> linkedList = new LinkedList<>(students);
        linkedList.addFirst(s);
        this.students = linkedList;
        writeFile(FILE_PATH, students);
    }

    @Override
    public void addLast(Student s) {
        LinkedList<Student> linkedList = new LinkedList<>(students);
        linkedList.addLast(s);
        this.students = linkedList;
        writeFile(FILE_PATH, students);
    }

    @Override
    public void removeFirst() {
        if (!students.isEmpty()) {
            LinkedList<Student> linkedList = new LinkedList<>(students);
            linkedList.removeFirst();
            this.students = linkedList;
            writeFile(FILE_PATH, students);
        }
    }

    @Override
    public void removeLast() {
        if (!students.isEmpty()) {
            LinkedList<Student> linkedList = new LinkedList<>(students);
            linkedList.removeLast();
            this.students = linkedList;
            writeFile(FILE_PATH, students);
        }
    }

    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public void add(Student s){
        if (s.getId() == null) {
            s.setId(generateId());
        }

        students.add(s);
        writeFile(FILE_PATH, students);
    }

    @Override
    public void delete(Long id) {
        students.removeIf(s -> s.getId().equals(id));
        writeFile(FILE_PATH, students);
    }

    // === UTILITY METHOD ===

    private Long generateId() {
        if (students.isEmpty()) return 1L;

        return students.stream()
                .mapToLong(Student::getId)
                .max()
                .orElse(0L) + 1;
    }

    private static List<Student> readFile(String path){
        List<Student> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                list.add(new Student(
                        Long.parseLong(data[0]),
                        data[1],
                        Double.parseDouble(data[2])
                ));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    private static void writeFile(String path, List<Student> list){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            for (Student student : list){
                bw.write(student.toFileString());
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

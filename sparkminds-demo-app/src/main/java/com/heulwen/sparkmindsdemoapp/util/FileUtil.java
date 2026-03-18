package com.heulwen.sparkmindsdemoapp.util;

import com.heulwen.sparkmindsdemoapp.models.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<Student> readFile(String path){
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

    public static void writeFile(String path, List<Student> list){
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

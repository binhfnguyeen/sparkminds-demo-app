package com.heulwen.sparkmindsdemoapp.services.impl;

import com.heulwen.sparkmindsdemoapp.models.Student;
import com.heulwen.sparkmindsdemoapp.services.FileHandlingService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileHandlingServiceImpl implements FileHandlingService {

    // ===== TXT =====
    @Override
    public File exportTxt(List<Student> students) throws IOException {
        File file = new File("students.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Student s : students) {
                writer.write(s.getId() + " - " + s.getName() + " - " + s.getScore());
                writer.newLine();
            }
        }
        return file;
    }

    @Override
    public File exportCsv(List<Student> students) throws Exception {
        File file = new File("students.csv");

        try (Writer writer = new FileWriter(file)) {
            StatefulBeanToCsv<Student> beanToCsv =
                    new StatefulBeanToCsvBuilder<Student>(writer).build();

            beanToCsv.write(students);
        }
        return file;
    }

    @Override
    public File exportExcel(List<Student> students) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        int rowNum = 0;

        // Header
        Row header = sheet.createRow(rowNum++);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Score");

        // Data
        for (Student s : students) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(s.getId());
            row.createCell(1).setCellValue(s.getName());
            row.createCell(2).setCellValue(s.getScore());
        }

        File file = new File("students.xlsx");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        }

        workbook.close();
        return file;
    }

    @Override
    public List<Student> readTxt(InputStream inputStream) throws IOException {
        List<Student> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    list.add(new Student(
                            Long.parseLong(parts[0]),
                            parts[1],
                            Double.parseDouble(parts[2])
                    ));
                }
            }
        }
        return list;
    }

    @Override
    public List<Student> readCsv(InputStream inputStream) throws Exception {
        Reader reader = new InputStreamReader(inputStream);

        CsvToBean<Student> csvToBean = new CsvToBeanBuilder<Student>(reader)
                .withType(Student.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        return csvToBean.parse();
    }

    @Override
    public List<Student> readExcel(InputStream inputStream) throws IOException {
        List<Student> list = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            if (row != null) {
                list.add(new Student(
                        (long) row.getCell(0).getNumericCellValue(),
                        row.getCell(1).getStringCellValue(),
                        row.getCell(2).getNumericCellValue()
                ));
            }
        }

        workbook.close();
        return list;
    }
}

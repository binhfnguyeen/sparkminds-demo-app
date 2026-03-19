package com.heulwen.sparkmindsdemoapp.services;

import com.heulwen.sparkmindsdemoapp.models.Student;
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
}

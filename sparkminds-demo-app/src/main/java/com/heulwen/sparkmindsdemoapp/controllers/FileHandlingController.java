package com.heulwen.sparkmindsdemoapp.controllers;

import com.heulwen.sparkmindsdemoapp.models.Student;
import com.heulwen.sparkmindsdemoapp.services.FileHandlingService;
import com.heulwen.sparkmindsdemoapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileHandlingController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private FileHandlingService fileHandlingService;

    @GetMapping("/file-demo")
    public String fileDemo(Model model) {
        model.addAttribute("type", "FILE HANDLING DEMO");
        model.addAttribute("desc", "TXT vs CSV vs Excel");
        return "file-view";
    }

    @GetMapping("/file-demo/export-txt")
    public ResponseEntity<InputStreamResource> exportTxt() throws Exception {
        List<Student> students = studentService.getStudents();
        File file = fileHandlingService.exportTxt(students);

        return buildResponse(file, "students.txt");
    }

    @GetMapping("/file-demo/export-csv")
    public ResponseEntity<InputStreamResource> exportCsv() throws Exception {
        File file = fileHandlingService.exportCsv(studentService.getStudents());
        return buildResponse(file, "students.csv");
    }

    @GetMapping("/file-demo/export-excel")
    public ResponseEntity<InputStreamResource> exportExcel() throws Exception {
        File file = fileHandlingService.exportExcel(studentService.getStudents());
        return buildResponse(file, "students.xlsx");
    }

    @PostMapping("/file-demo/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {

        List<Student> students = new ArrayList<>();

        try {
            String filename = file.getOriginalFilename();

            if (filename.endsWith(".txt")) {
                students = fileHandlingService.readTxt(file.getInputStream());
            } else if (filename.endsWith(".csv")) {
                students = fileHandlingService.readCsv(file.getInputStream());
            } else if (filename.endsWith(".xlsx")) {
                students = fileHandlingService.readExcel(file.getInputStream());
            }

            model.addAttribute("students", students);
            model.addAttribute("message", "Read success: " + filename);

        } catch (Exception e) {
            model.addAttribute("message", "Error reading file!");
        }

        model.addAttribute("type", "FILE READ DEMO");

        return "file-view";
    }

    @GetMapping("/file-demo/interactive")
    public String showInteractiveDemo(Model model) {
        try {
            String currentContent = fileHandlingService.readInteractiveFile();
            model.addAttribute("fileContent", currentContent);
        } catch (Exception e) {
            model.addAttribute("fileContent", "Lỗi khi đọc file!");
        }

        model.addAttribute("type", "RANDOM ACCESS INTERACTIVE");
        return "file-view";
    }

    @PostMapping("/file-demo/interactive/write")
    public String processInteractiveWrite(
            @RequestParam("content") String content,
            @RequestParam(value = "position", required = false) Long position,
            @RequestParam("actionType") String actionType,
            Model model) {

        try {
            boolean isAppend = "append".equals(actionType);

            // Xử lý xuống dòng nếu người dùng muốn thêm vào cuối
            String contentToWrite = isAppend ? "\n" + content : content;

            String updatedContent = fileHandlingService.writeInteractiveFile(contentToWrite, position, isAppend);

            model.addAttribute("message", "Thao tác thành công!");
            model.addAttribute("fileContent", updatedContent);
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi xử lý tệp tin: " + e.getMessage());
        }

        model.addAttribute("type", "RANDOM ACCESS INTERACTIVE");
        return "file-view";
    }

    private ResponseEntity<InputStreamResource> buildResponse(File file, String filename) throws Exception {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(new FileInputStream(file)));
    }
}

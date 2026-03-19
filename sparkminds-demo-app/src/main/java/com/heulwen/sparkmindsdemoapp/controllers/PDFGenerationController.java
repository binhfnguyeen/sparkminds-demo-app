package com.heulwen.sparkmindsdemoapp.controllers;

import com.heulwen.sparkmindsdemoapp.services.PDFGenerationService;
import com.heulwen.sparkmindsdemoapp.services.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PDFGenerationController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private PDFGenerationService pdfGenerationService;

    @GetMapping("/file-demo/export-pdf-hardcode")
    public void exportPdfHardcode(HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=students.pdf");

        pdfGenerationService.exportPdfHardcode(response.getOutputStream(), studentService.getStudents());
    }

    @GetMapping("/file-demo/export-pdf-template")
    public void exportPdfTemplate(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=students_template.pdf");
        pdfGenerationService.exportPdfTemplate(response.getOutputStream(), studentService.getStudents());
    }
}

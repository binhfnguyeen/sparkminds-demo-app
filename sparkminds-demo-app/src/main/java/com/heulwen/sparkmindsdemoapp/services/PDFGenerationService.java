package com.heulwen.sparkmindsdemoapp.services;

import com.heulwen.sparkmindsdemoapp.models.Student;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface PDFGenerationService {
    void exportPdfHardcode(OutputStream outputStream, List<Student> students) throws DocumentException;
    void exportPdfTemplate(OutputStream outputStream, List<Student> students) throws IOException;
}

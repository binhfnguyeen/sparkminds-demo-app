package com.heulwen.sparkmindsdemoapp.services.impl;

import com.heulwen.sparkmindsdemoapp.models.Student;
import com.heulwen.sparkmindsdemoapp.services.PDFGenerationService;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class PDFGenerationServiceImpl implements PDFGenerationService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void exportPdfHardcode(OutputStream outputStream, List<Student> students) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        document.add(new Paragraph("STUDENT LIST"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Score");

        for (Student s : students) {
            table.addCell(String.valueOf(s.getId()));
            table.addCell(s.getName());
            table.addCell(String.valueOf(s.getScore()));
        }

        document.add(table);
        document.close();
    }

    @Override
    public void exportPdfTemplate(OutputStream outputStream, List<Student> students) throws IOException {
        Context context = new Context();
        context.setVariable("students", students);

        String htmlContent = templateEngine.process("pdf-template", context);

        HtmlConverter.convertToPdf(htmlContent, outputStream);
    }
}

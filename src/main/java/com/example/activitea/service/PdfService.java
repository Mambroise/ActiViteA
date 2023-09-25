package com.example.activitea.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {
    public byte[] createPDFFromCoverLetter(String coverLetterContent) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        // Split the cover letter content into paragraphs
        String[] paragraphs = coverLetterContent.split("\\n\\n");

        // Add paragraphs to the PDF
        for (String paragraph : paragraphs) {
            document.add(new Paragraph(paragraph));
        }

        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}

package com.example.activitea.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
    
    public void savePDFToFile(byte[] pdfBytes, String filePath) throws IOException {
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(pdfBytes);
        }
    }
}

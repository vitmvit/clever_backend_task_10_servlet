package org.example.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.example.exception.CheckGenerateException;
import org.example.model.dto.CatDto;
import org.example.service.PdfService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.example.constant.Constant.FILE_BASE_CHECK;
import static org.example.constant.Constant.PDF;

/**
 * Класс CheckPdfServiceImpl реализует интерфейс PdfService и предоставляет функционал для создания чека в формате PDF на основе объекта CatDto.
 */
public class CheckPdfServiceImpl implements PdfService {

    @SneakyThrows
    @Override
    public void create(CatDto catDto) {
        try {
            Document document = new Document(PageSize.A6);
            PdfWriter.getInstance(document, new FileOutputStream(FILE_BASE_CHECK + catDto.getName() + PDF));
            document.open();
            Paragraph paragraph = new Paragraph();
            paragraph.add("Cat name: " + catDto.getName() + "\n");
            paragraph.add("Color: " + catDto.getColor() + "\n");
            paragraph.add("Date: " + LocalDateTime.now() + "\n");
            document.add(paragraph);
            document.close();
        } catch (DocumentException | IOException e) {
            throw new CheckGenerateException(e);
        }
    }
}

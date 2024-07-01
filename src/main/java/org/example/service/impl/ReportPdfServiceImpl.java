package org.example.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.exception.ReportGenerateException;
import org.example.model.dto.CatDto;
import org.example.observer.Observer;
import org.example.service.PdfService;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.Date;

import static org.example.constant.Constant.*;

/**
 * Класс ReportPdfServiceImpl реализует интерфейс Observer и предоставляет методы для создания PDF-отчетов на основе объектов CatDto.
 * Отчеты генерируются в формате PDF с использованием библиотеки iText.
 * Отчеты генерируются при получении обновлений о создании новых котов.
 */
@Service
public class ReportPdfServiceImpl implements Observer, PdfService {

    /**
     * Создает PDF-отчет на основе объекта CatDto.
     * Отчет включает информацию о коте, такую как имя, порода, цвет и возраст.
     * Отчет также включает заголовок, данные о генерации и фоновое изображение.
     *
     * @param catDto объект CatDto, на основе которого создается отчет
     * @throws ReportGenerateException если возникла ошибка при генерации отчета
     */
    public void create(CatDto catDto) {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE_BASE_REPORT + catDto.getName() + PDF));
            document.open();
            Paragraph paragraph = new Paragraph();
            addEmptyLine(paragraph, 10);
            paragraph.add(new Paragraph("Cats report", FONT_TITLE));
            paragraph.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(), FONT_DATA));
            addEmptyLine(paragraph, 1);
            paragraph.add(new Paragraph("Cat name: " + catDto.getName(), FONT_BODY));
            paragraph.add(new Paragraph("Cat beer: " + catDto.getBreed(), FONT_BODY));
            paragraph.add(new Paragraph("Cat color: " + catDto.getColor(), FONT_BODY));
            paragraph.add(new Paragraph("Cat age: " + catDto.getAge(), FONT_BODY));
            document.add(paragraph);
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image = Image.getInstance(BACKGROUND_IMAGE);
            image.scaleAbsolute(PageSize.A4);
            image.setAbsolutePosition(0, 0);
            canvas.addImage(image);
            document.close();
        } catch (Exception e) {
            throw new ReportGenerateException(e);
        }
    }

    /**
     * Вызывается при получении обновления о создании нового кота.
     * Создает PDF-отчет на основе переданного объекта CatDto, используя метод createPdf.
     *
     * @param catDto объект CatDto, на основе которого создается отчет
     */
    @Override
    public void update(CatDto catDto) {
        create(catDto);
    }

    /**
     * Добавляет пустые строки в заданное абзаце указанное количество раз.
     *
     * @param paragraph абзац для добавления пустых строк
     * @param number    количество пустых строк для добавления
     */
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}

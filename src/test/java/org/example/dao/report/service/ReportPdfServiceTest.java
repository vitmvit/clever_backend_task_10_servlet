package org.example.dao.report.service;

import org.example.dao.util.CatTestData;
import org.example.dao.util.FileUtil;
import org.example.model.dto.CatDto;
import org.example.service.PdfService;
import org.example.service.impl.ReportPdfServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

import static org.example.constant.Constant.DIRECTORY_REPORT;

public class ReportPdfServiceTest {

    private final PdfService pdfService = new ReportPdfServiceImpl();

    @BeforeAll
    public static void setUp() {
        FileUtil.deleteFilesInDirectory(DIRECTORY_REPORT);
    }

    @AfterAll
    public static void after() {
        FileUtil.deleteFilesInDirectory(DIRECTORY_REPORT);
    }

    @Test
    public void createPdfShouldCheckDirectoryForEmpty() {
        CatDto catDto = CatTestData.builder().build().buildCatDto();
        pdfService.create(catDto);
        File reportDir = new File(DIRECTORY_REPORT);
        Assertions.assertTrue(Objects.requireNonNull(reportDir.listFiles()).length > 0);
    }
}


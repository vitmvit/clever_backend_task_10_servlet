package org.example.dao.report.service;

import org.example.dao.util.CatTestData;
import org.example.dao.util.FileUtil;
import org.example.model.dto.CatDto;
import org.example.service.PdfService;
import org.example.service.impl.CheckPdfServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

import static org.example.constant.Constant.DIRECTORY_CHECK;

public class CheckPdfServiceTest {
    private final PdfService pdfService = new CheckPdfServiceImpl();

    @BeforeAll
    public static void setUp() {
        FileUtil.deleteFilesInDirectory(DIRECTORY_CHECK);
    }

    @AfterAll
    public static void after() {
        FileUtil.deleteFilesInDirectory(DIRECTORY_CHECK);
    }

    @Test
    public void createPdfShouldCheckDirectoryForEmpty() {
        CatDto catDto = CatTestData.builder().build().buildCatDto();
        pdfService.create(catDto);
        File reportDir = new File(DIRECTORY_CHECK);
        Assertions.assertTrue(Objects.requireNonNull(reportDir.listFiles()).length > 0);
    }
}

package org.example.observer.impl;

import org.example.model.dto.CatDto;
import org.example.observer.Observer;
import org.example.pdf.service.impl.ReportPdfServiceImpl;

public class ObserverImpl implements Observer {
    private ReportPdfServiceImpl pdfService;

    public ObserverImpl(ReportPdfServiceImpl pdfService) {
        this.pdfService = pdfService;
    }

    @Override
    public void update(CatDto catDto) {
        pdfService.create(catDto);
    }
}
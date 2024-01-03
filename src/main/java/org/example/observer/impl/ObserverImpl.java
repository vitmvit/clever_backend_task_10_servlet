package org.example.observer.impl;

import org.example.model.dto.CatDto;
import org.example.observer.Observer;
import org.example.service.impl.ReportPdfServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class ObserverImpl implements Observer {
    private final ReportPdfServiceImpl pdfService;

    public ObserverImpl(ReportPdfServiceImpl pdfService) {
        this.pdfService = pdfService;
    }

    @Override
    public void update(CatDto catDto) {
        pdfService.create(catDto);
    }
}
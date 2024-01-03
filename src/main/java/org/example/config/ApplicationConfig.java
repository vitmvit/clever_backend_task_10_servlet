package org.example.config;

import org.example.converter.CatConverter;
import org.example.converter.CatConverterImpl;
import org.example.dao.CatDao;
import org.example.dao.impl.CatDaoImpl;
import org.example.service.CatService;
import org.example.service.PdfService;
import org.example.service.impl.CatServiceImpl;
import org.example.service.impl.CheckPdfServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CatDao catDao() {
        return new CatDaoImpl();
    }

    @Bean
    public CatConverter catConverter() {
        return new CatConverterImpl();
    }

    @Bean
    public CatService catService() {
        return new CatServiceImpl(catDao(), catConverter());
    }

    @Bean
    public PdfService checkPdfService() {
        return new CheckPdfServiceImpl();
    }
}

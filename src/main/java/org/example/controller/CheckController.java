//package org.example.controller;
//
//import lombok.AllArgsConstructor;
//import org.example.converter.CatConverter;
//import org.example.converter.CatConverterImpl;
//import org.example.dao.CatDao;
//import org.example.dao.impl.CatDaoImpl;
//import org.example.model.dto.CatDto;
//import org.example.service.PdfService;
//import org.example.service.impl.CheckPdfServiceImpl;
//import org.example.service.CatService;
//import org.example.service.impl.CatServiceImpl;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static org.example.constant.Constant.CAT_NOT_FOUND_ERROR;
//import static org.example.constant.Constant.CHECK_WAS_CREATED_MESSAGE;
//
///**
// * Класс CheckController является сервлетом и обрабатывает GET-запросы для создания чека PDF на основе объекта Cat.
// * Получает идентификатор объекта Cat из параметров запроса и создает чек PDF с помощью сервиса PdfService.
// * Отправляет соответствующий ответ на запрос.
// */
//@Controller
//@WebServlet
//public class CheckController extends HttpServlet {
//
////    private final CatService catService;
////    private final PdfService pdfService;
////
////    public CheckController(CatService catService,
////                           @Qualifier("checkPdfServiceImpl") PdfService pdfService) {
////        this.catService = catService;
////        this.pdfService = pdfService;
////    }
//
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            response.getOutputStream().println("Yuuuuu2");
//            response.setStatus(200);
//        } catch (Exception e) {
//            response.getOutputStream().println("404");
//            response.setStatus(404);
//        }
//    }
//
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
////        String idParam = request.getParameter("id");
////
////        if (idParam != null) {
////            try {
////                CatDto object = catService.getById(Long.parseLong(idParam));
////
////                pdfService.create(object);
////
////                response.getOutputStream().println(CHECK_WAS_CREATED_MESSAGE);
////                response.setStatus(200);
////            } catch (Exception e) {
////                response.getOutputStream().println(CAT_NOT_FOUND_ERROR);
////                response.setStatus(404);
////            }
////        }
////    }
//}

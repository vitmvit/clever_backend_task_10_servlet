package org.example.controller;

import org.example.converter.CatConverter;
import org.example.converter.CatConverterImpl;
import org.example.dao.CatDao;
import org.example.dao.impl.CatDaoImpl;
import org.example.model.dto.CatDto;
import org.example.pdf.service.PdfService;
import org.example.pdf.service.impl.CheckPdfServiceImpl;
import org.example.service.CatService;
import org.example.service.impl.CatServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.example.constant.Constant.CAT_NOT_FOUND_ERROR;
import static org.example.constant.Constant.CHECK_WAS_CREATED_MESSAGE;

@WebServlet
public class CheckController extends HttpServlet {

    private final CatDao dao = new CatDaoImpl();
    private final CatConverter converter = new CatConverterImpl();
    private final CatService catService = new CatServiceImpl(dao, converter);
    private final PdfService pdfService = new CheckPdfServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                CatDto object = catService.getById(Long.parseLong(idParam));

                pdfService.create(object);

                response.getOutputStream().println(CHECK_WAS_CREATED_MESSAGE);
                response.setStatus(200);
            } catch (Exception e) {
                response.getOutputStream().println(CAT_NOT_FOUND_ERROR);
                response.setStatus(404);
            }
        }
    }
}

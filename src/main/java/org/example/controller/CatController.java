package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.converter.CatConverter;
import org.example.converter.CatConverterImpl;
import org.example.dao.CatDao;
import org.example.dao.impl.CatDaoImpl;
import org.example.model.dto.CatCreateDto;
import org.example.model.dto.CatDto;
import org.example.model.dto.CatUpdateDto;
import org.example.service.CatService;
import org.example.service.impl.CatServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.example.constant.Constant.*;

@WebServlet
public class CatController extends HttpServlet {

    private final CatDao dao = new CatDaoImpl();
    private final CatConverter converter = new CatConverterImpl();
    private final CatService service = new CatServiceImpl(dao, converter);

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        String pageParam = request.getParameter("page");
        String countParam = request.getParameter("count");

        // Обработка запроса по параметру "id"
        if (idParam != null) {
            try {
                CatDto object = service.getById(Long.parseLong(idParam));
                ObjectMapper objectMapper = new ObjectMapper();

                response.getOutputStream().println(objectMapper.writeValueAsString(object));
                response.setStatus(200);
            } catch (Exception e) {
                response.getOutputStream().println(CAT_NOT_FOUND_ERROR);
                response.setStatus(404);
            }
            return;
        }

        // Обработка запроса по параметрам "page" и "count"
        if (pageParam != null && countParam != null) {
            try {
                List<CatDto> objects = service.getListByPageAndCount(Integer.parseInt(pageParam), Integer.parseInt(countParam));

                if (!objects.isEmpty()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    response.getOutputStream().println(objectMapper.writeValueAsString(objects));
                } else {
                    response.getOutputStream().println(NO_SUCH_DATA_MESSAGE);
                }
                response.setStatus(200);
            } catch (Exception e) {
                response.getOutputStream().println(RETRIEVING_ERROR);
                response.setStatus(500);
            }
            return;
        }

        // Обработка запроса по параметру "page"
        if (pageParam != null) {
            try {
                List<CatDto> objects = service.getListByPageAndCount(Integer.parseInt(pageParam), PAGE_SIZE);
                if (!objects.isEmpty()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    response.getOutputStream().println(objectMapper.writeValueAsString(objects));
                } else {
                    response.getOutputStream().println(NO_SUCH_DATA_MESSAGE);
                }
                response.setStatus(200);
            } catch (Exception e) {
                response.getOutputStream().println(RETRIEVING_ERROR);
                response.setStatus(500);
            }
            return;
        }

        // Обработка запроса по параметру "count"
        if (countParam != null) {
            try {
                List<CatDto> objects = service.getListByPageAndCount(PAGE_NUMBER, Integer.parseInt(countParam));
                if (!objects.isEmpty()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    response.getOutputStream().println(objectMapper.writeValueAsString(objects));
                } else {
                    response.getOutputStream().println(NO_SUCH_DATA_MESSAGE);
                }
                response.setStatus(200);
            } catch (Exception e) {
                response.getOutputStream().println(RETRIEVING_ERROR);
                response.setStatus(500);
            }
            return;
        }

        response.getOutputStream().println(INVALID_REQUEST_PARAMETERS_ERROR);
        response.setStatus(400);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuilder jb = new StringBuilder();
            String line;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            CatCreateDto catCreateDto = objectMapper.readValue(jb.toString(), CatCreateDto.class);
            CatDto catDto = service.create(catCreateDto);

            response.getOutputStream().println(objectMapper.writeValueAsString(catDto));
            response.setStatus(200);
        } catch (Exception e) {
            response.getOutputStream().println(CAT_CREATED_ERROR);
            response.setStatus(500);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuilder jb = new StringBuilder();
            String line = null;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            CatDto catDto = service.update(objectMapper.readValue(jb.toString(), CatUpdateDto.class));

            response.getOutputStream().println(objectMapper.writeValueAsString(catDto));
            response.setStatus(200);
        } catch (Exception e) {
            response.getOutputStream().println(CAT_UPDATED_ERROR);
            response.setStatus(500);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            service.delete(Long.valueOf(request.getParameter("id")));
            response.getOutputStream().println(CAT_IS_DELETED_MESSAGE);
            response.setStatus(200);
        } catch (Exception e) {
            response.getOutputStream().println(CAT_DELETED_ERROR);
            response.setStatus(500);
        }
    }
}

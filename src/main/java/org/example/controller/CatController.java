package org.example.controller;

import org.example.config.ApplicationConfig;
import org.example.service.impl.ServiceG;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс CatController является сервлетом и обрабатывает запросы, связанные с объектами Cat.
 *
 * @author Витикова Мария
 */
//@AllArgsConstructor
@Component
@WebServlet
public class CatController extends HttpServlet {

    //    @Autowired
//    private final CatService catService;
    private AnnotationConfigApplicationContext context;

    //    @Autowired
    private ServiceG catService;

    public CatController() {
        this.context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        catService = context.getBean(ServiceG.class);
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * Метод обрабатывает GET-запросы, связанные с объектами Cat.
     * В зависимости от параметров запроса "id", "page" и "count" выполняет соответствующую обработку запроса.
     * Отправляет соответствующий ответ на запрос.
     *
     * @param request  объект HttpServletRequest для получения параметров запроса
     * @param response объект HttpServletResponse для отправки ответа
     * @throws IOException в случае ошибки при работе с потоком вывода
     */

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
//            response.getOutputStream().println("kkk");
            response.getOutputStream().println(catService.getHello());
            response.setStatus(200);
        } catch (Exception e) {
            response.getOutputStream().println("404");
            response.setStatus(404);
        }
    }
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String idParam = request.getParameter("id");
//        String pageParam = request.getParameter("page");
//        String countParam = request.getParameter("count");
//
//        // Обработка запроса по параметру "id"
//        if (idParam != null) {
//            try {
//                CatDto object = catService.getById(Long.parseLong(idParam));
//                ObjectMapper objectMapper = new ObjectMapper();
//
//                response.getOutputStream().println(objectMapper.writeValueAsString(object));
//                response.setStatus(200);
//            } catch (Exception e) {
//                response.getOutputStream().println(CAT_NOT_FOUND_ERROR);
//                response.setStatus(404);
//            }
//            return;
//        }
//
//        // Обработка запроса по параметрам "page" и "count"
//        if (pageParam != null && countParam != null) {
//            try {
//                List<CatDto> objects = catService.getListByPageAndCount(Integer.parseInt(pageParam), Integer.parseInt(countParam));
//
//                if (!objects.isEmpty()) {
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    response.getOutputStream().println(objectMapper.writeValueAsString(objects));
//                } else {
//                    response.getOutputStream().println(NO_SUCH_DATA_MESSAGE);
//                }
//                response.setStatus(200);
//            } catch (Exception e) {
//                response.getOutputStream().println(RETRIEVING_ERROR);
//                response.setStatus(500);
//            }
//            return;
//        }
//
//        // Обработка запроса по параметру "page"
//        if (pageParam != null) {
//            try {
//                List<CatDto> objects = catService.getListByPageAndCount(Integer.parseInt(pageParam), PAGE_SIZE);
//                if (!objects.isEmpty()) {
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    response.getOutputStream().println(objectMapper.writeValueAsString(objects));
//                } else {
//                    response.getOutputStream().println(NO_SUCH_DATA_MESSAGE);
//                }
//                response.setStatus(200);
//            } catch (Exception e) {
//                response.getOutputStream().println(RETRIEVING_ERROR);
//                response.setStatus(500);
//            }
//            return;
//        }
//
//        // Обработка запроса по параметру "count"
//        if (countParam != null) {
//            try {
//                List<CatDto> objects = catService.getListByPageAndCount(PAGE_NUMBER, Integer.parseInt(countParam));
//                if (!objects.isEmpty()) {
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    response.getOutputStream().println(objectMapper.writeValueAsString(objects));
//                } else {
//                    response.getOutputStream().println(NO_SUCH_DATA_MESSAGE);
//                }
//                response.setStatus(200);
//            } catch (Exception e) {
//                response.getOutputStream().println(RETRIEVING_ERROR);
//                response.setStatus(500);
//            }
//            return;
//        }
//
//        response.getOutputStream().println(INVALID_REQUEST_PARAMETERS_ERROR);
//        response.setStatus(400);
//    }
//
//    /**
//     * Метод обрабатывает POST-запросы, связанные с созданием объекта Cat.
//     * Читает переданные данные запроса и преобразует их в объект CatCreateDto.
//     * Создает новый объект CatDto с помощью метода create() сервиса.
//     * Отправляет созданный объект CatDto в качестве ответа на запрос.
//     *
//     * @param request  объект HttpServletRequest для получения данных запроса
//     * @param response объект HttpServletResponse для отправки ответа
//     * @throws IOException в случае ошибки при работе с потоком вывода
//     */
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            StringBuilder jb = new StringBuilder();
//            String line;
//            BufferedReader reader = request.getReader();
//            while ((line = reader.readLine()) != null) {
//                jb.append(line);
//            }
//            ObjectMapper objectMapper = new ObjectMapper();
//            CatCreateDto catCreateDto = objectMapper.readValue(jb.toString(), CatCreateDto.class);
//            CatDto catDto = catService.create(catCreateDto);
//
//            response.getOutputStream().println(objectMapper.writeValueAsString(catDto));
//            response.setStatus(200);
//        } catch (Exception e) {
//            response.getOutputStream().println(CAT_CREATED_ERROR);
//            response.setStatus(500);
//        }
//    }
//
//    /**
//     * Метод обрабатывает PUT-запросы, связанные с обновлением объекта Cat.
//     * Читает переданные данные запроса и преобразует их в объект CatUpdateDto.
//     * Обновляет объект CatDto с помощью метода update() сервиса.
//     * Отправляет обновленный объект CatDto в качестве ответа на запрос.
//     *
//     * @param request  объект HttpServletRequest для получения данных запроса
//     * @param response объект HttpServletResponse для отправки ответа
//     * @throws IOException в случае ошибки при работе с потоком вывода
//     */
//    @Override
//    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            StringBuilder jb = new StringBuilder();
//            String line = null;
//            BufferedReader reader = request.getReader();
//            while ((line = reader.readLine()) != null) {
//                jb.append(line);
//            }
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            CatDto catDto = catService.update(objectMapper.readValue(jb.toString(), CatUpdateDto.class));
//
//            response.getOutputStream().println(objectMapper.writeValueAsString(catDto));
//            response.setStatus(200);
//        } catch (Exception e) {
//            response.getOutputStream().println(CAT_UPDATED_ERROR);
//            response.setStatus(500);
//        }
//    }
//
//    /**
//     * Метод обрабатывает DELETE-запросы, связанные с удалением объекта Cat.
//     * Получает идентификатор объекта Cat из параметров запроса и удаляет объект с помощью метода delete() сервиса.
//     * Отправляет сообщение об успешном удалении в качестве ответа на запрос.
//     *
//     * @param request  объект HttpServletRequest для получения данных запроса
//     * @param response объект HttpServletResponse для отправки ответа
//     * @throws IOException в случае ошибки при работе с потоком вывода
//     */
//    @Override
//    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            catService.delete(Long.valueOf(request.getParameter("id")));
//            response.getOutputStream().println(CAT_IS_DELETED_MESSAGE);
//            response.setStatus(200);
//        } catch (Exception e) {
//            response.getOutputStream().println(CAT_DELETED_ERROR);
//            response.setStatus(500);
//        }
//    }
}

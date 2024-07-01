package org.example.listener;

import liquibase.integration.spring.SpringLiquibase;
import org.example.config.DatasourceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Класс ContextListener является слушателем контекста сервлета и выполняет инициализацию приложения при старте контекста.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private AnnotationConfigApplicationContext context;

    /**
     * Метод, вызываемый при инициализации контекста приложения.
     * Создает и инициализирует объект контекста приложения с помощью класса DatasourceConfig.
     * Получает бин SpringLiquibase из контекста.
     *
     * @param event объект, описывающий событие инициализации контекста
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        context = new AnnotationConfigApplicationContext(DatasourceConfig.class);
        context.getBean(SpringLiquibase.class);
    }

    /**
     * Метод, вызываемый при разрушении контекста приложения.
     * Закрывает контекст приложения.
     *
     * @param servletContextEvent объект, описывающий событие разрушения контекста
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        context.close();
    }
}

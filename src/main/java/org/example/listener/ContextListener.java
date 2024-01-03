//package org.example.listener;
//
//import liquibase.Contexts;
//import liquibase.LabelExpression;
//import liquibase.Liquibase;
//import liquibase.database.Database;
//import liquibase.database.DatabaseFactory;
//import liquibase.database.jvm.JdbcConnection;
//import liquibase.exception.LiquibaseException;
//import liquibase.resource.ClassLoaderResourceAccessor;
//import org.example.config.ApplicationConfig;
//import org.example.config.ConfigReader;
////import org.example.connection.DatabaseConnector;
////import org.example.connection.DbConnection;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.AbstractApplicationContext;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.sql.Connection;
//import java.util.Map;
//
//import static org.example.constant.Constant.*;
//
///**
// * Класс ContextListener является слушателем контекста сервлета и выполняет инициализацию приложения при старте контекста.
// */
////@WebListener
////public class ContextListener implements ServletContextListener {
////
////    private ApplicationContext context;
////    /**
////     * Метод выполняет инициализацию приложения при старте контекста сервлета.
////     * Извлекает конфигурацию из файла с помощью ConfigReader.
////     * Если настройка ENABLED_SOURCE имеет значение true, устанавливает соединение с базой данных с помощью DatabaseConnector.
////     * Если соединение установлено успешно, выполняет инициализацию базы данных с помощью Liquibase.
////     *
////     * @param event объект ServletContextEvent для получения событий контекста сервлета
////     */
////    @Override
////    public void contextInitialized(ServletContextEvent event) {
////        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
////
////        // Получение бинов из контекста
////        ConfigReader configReader = context.getBean(ConfigReader.class);
////
////        Map<String, String> configMap = configReader.getConfigMap();
////        if (Boolean.parseBoolean(configMap.get(ENABLED_SOURCE))) {
////            Connection connection = new DatabaseConnector().connection(
////                    configMap.get(DRIVER_SOURCE),
////                    configMap.get(URL_SOURCE),
////                    configMap.get(USERNAME_SOURCE),
////                    configMap.get(PASSWORD_SOURCE)
////            );
////            if (connection != null) {
////                Database database;
////                try {
////                    database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
////                    Liquibase liquibase = new liquibase.Liquibase(configMap.get(CHANGELOG_SOURCE), new ClassLoaderResourceAccessor(), database);
////                    for (Map.Entry<String, String> item : configMap.entrySet()) {
////                        liquibase.setChangeLogParameter(item.getKey(), item.getValue());
////                    }
////                    liquibase.update(new Contexts(), new LabelExpression());
////                } catch (LiquibaseException e) {
////                    throw new RuntimeException(e);
////                }
////            } else {
////                throw new RuntimeException(CONNECTION_ERROR);
////            }
////        }
////    }
////}
//
//@WebListener
//public class ContextListener implements ServletContextListener {
//
//    private AbstractApplicationContext context;
//
//    @Override
//    public void contextInitialized(ServletContextEvent event) {
//        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        ConfigReader configReader = context.getBean(ConfigReader.class);
//        Map<String, String> configMap = configReader.getConfigMap();
//
//        if (Boolean.parseBoolean(configMap.get(ENABLED_SOURCE))) {
////            DatabaseConnector databaseConnector = context.getBean(DatabaseConnector.class);
////            DbConnection dbConnection = context.getBean(DbConnection.class);
//
//            Connection connection = databaseConnector.connection(
//                    configMap.get("driver"),
//                    configMap.get("url"),
//                    configMap.get("username"),
//                    configMap.get("password")
//            );
//            if (connection != null) {
//                Database database;
//                try {
//                    database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
//                    Liquibase liquibase = new liquibase.Liquibase(configMap.get("changelog"), new ClassLoaderResourceAccessor(), database);
//                    for (Map.Entry<String, String> item : configMap.entrySet()) {
//                        liquibase.setChangeLogParameter(item.getKey(), item.getValue());
//                    }
//                    liquibase.update(new Contexts(), new LabelExpression());
//                } catch (LiquibaseException e) {
//                    throw new RuntimeException(e);
//                }
//            } else {
//                throw new RuntimeException(CONNECTION_ERROR);
//            }
//        }
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent event) {
//        context.close();
//    }
//}
//

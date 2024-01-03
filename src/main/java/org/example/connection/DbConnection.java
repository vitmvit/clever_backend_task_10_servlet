//package org.example.connection;
//
//import lombok.AllArgsConstructor;
//import org.example.config.ConfigReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.example.constant.Constant.*;
//
//
//@Component
//public class DbConnection {
//
//    @Autowired
//    private ConfigReader configReader;
//
//    public Optional<Connection> getConnection() {
//        if (ConnectionSingleton.getInstance().getConnection().isEmpty()) {
//            createConnection();
//        }
//        return ConnectionSingleton.getInstance().getConnection();
//    }
//
//    private void createConnection() {
//        if (ConnectionSingleton.getInstance().getConnection().isEmpty()) {
//            Map<String, String> configMap = configReader.getConfigMap();
//            Connection connection = new DatabaseConnector().connection(
//                    configMap.get("driver"),
//                    configMap.get("url"),
//                    configMap.get("username"),
//                    configMap.get("password")
//            );
//            if (connection == null) {
//                ConnectionSingleton.setInstance(Optional.empty());
//            } else {
//                ConnectionSingleton.setInstance(Optional.of(connection));
//            }
//        }
//    }
////
////    private final ConfigReader configReader = new ConfigReader();
////    private final Map<String, String> configMap = configReader.getConfigMap();
////
////    public Optional<Connection> getConnection() {
////        if (ConnectionSingleton.getInstance().getConnection().isEmpty()) {
////            createConnection();
////        }
////        return ConnectionSingleton.getInstance().getConnection();
////    }
////
////    private void createConnection() {
////        if (ConnectionSingleton.getInstance().getConnection().isEmpty()) {
////            Connection connection = new DatabaseConnector().connection(
////                    configMap.get(DRIVER_SOURCE),
////                    configMap.get(URL_SOURCE),
////                    configMap.get(USERNAME_SOURCE),
////                    configMap.get(PASSWORD_SOURCE)
////            );
////            if (connection == null) {
////                ConnectionSingleton.setInstance(Optional.empty());
////            } else {
////                ConnectionSingleton.setInstance(Optional.of(connection));
////            }
////        }
////    }
//}
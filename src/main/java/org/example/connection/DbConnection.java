package org.example.connection;

import org.example.config.ConfigReader;

import java.sql.Connection;
import java.util.Map;
import java.util.Optional;

import static org.example.constant.Constant.*;

public class DbConnection {

    private final ConfigReader configReader = new ConfigReader();
    private final Map<String, String> configMap = configReader.getConfigMap();

    public Optional<Connection> getConnection() {
        if (ConnectionSingleton.getInstance().getConnection().isEmpty()) {
            createConnection();
        }
        return ConnectionSingleton.getInstance().getConnection();
    }

    private void createConnection() {
        if (ConnectionSingleton.getInstance().getConnection().isEmpty()) {
            Connection connection = new DatabaseConnector().connection(
                    configMap.get(DRIVER_SOURCE),
                    configMap.get(URL_SOURCE),
                    configMap.get(USERNAME_SOURCE),
                    configMap.get(PASSWORD_SOURCE)
            );
            if (connection == null) {
                ConnectionSingleton.setInstance(Optional.empty());
            } else {
                ConnectionSingleton.setInstance(Optional.of(connection));
            }
        }
    }
}
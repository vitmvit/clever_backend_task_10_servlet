package org.example.connection;

import org.example.config.ConfigReader;

import java.sql.Connection;
import java.util.Map;
import java.util.Optional;

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
                    configMap.get("driver"),
                    configMap.get("url"),
                    configMap.get("username"),
                    configMap.get("password")
            );
            if (connection == null) {
                ConnectionSingleton.setInstance(Optional.empty());
            } else {
                ConnectionSingleton.setInstance(Optional.of(connection));
            }
        }
    }
}
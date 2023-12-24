package org.example.listener;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.config.ConfigReader;
import org.example.connection.ConnectionSingleton;
import org.example.connection.DatabaseConnector;
import org.example.connection.DbConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.util.Map;
import java.util.Optional;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ConfigReader configReader = new ConfigReader();
        Map<String, String> configMap = configReader.getConfigMap();
        if (Boolean.parseBoolean(configMap.get("enabled"))) {
            Connection connection = new DatabaseConnector().connection(
                    configMap.get("driver"),
                    configMap.get("url"),
                    configMap.get("username"),
                    configMap.get("password")
            );
            if (connection != null) {
                Database database;
                try {
                    database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                    Liquibase liquibase = new liquibase.Liquibase(configMap.get("changelog"), new ClassLoaderResourceAccessor(), database);
                    for (Map.Entry<String, String> item : configMap.entrySet()) {
                        liquibase.setChangeLogParameter(item.getKey(), item.getValue());
                    }
                    liquibase.update(new Contexts(), new LabelExpression());
                } catch (LiquibaseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException("Connection error");
            }
        }
        buildConnect();
    }

    private void buildConnect() {
        Optional<Connection> dbConnection = new DbConnection().getConnection();
        ConnectionSingleton.setInstance(dbConnection);
    }
}


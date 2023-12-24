package org.example.listener;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;
import org.example.config.ConfigReader;
import org.example.connection.ConnectionSingleton;
import org.example.connection.DbConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@WebListener
public class ContextListener implements ServletContextListener {

    private final Map<String, String> configMap = new ConfigReader().getConfigMap();
    private final Connection connection = new DbConnection().getConnection().get();

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ConfigReader configReader = new ConfigReader();
        Map<String, String> configMap = configReader.getConfigMap();
        if (Boolean.parseBoolean(configMap.get("enabled"))) {
            Optional<Connection> connectionOptional = ConnectionSingleton.getInstance().getConnection();
            if (connectionOptional.isPresent()) {
                Connection connection = connectionOptional.get();
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                try (Liquibase liquibase = new liquibase.Liquibase(configMap.get("changelog"), new ClassLoaderResourceAccessor(), database)) {

                    //properties.forEach((key, value) -> liquibase.setChangeLogParameter(Objects.toString(key), value));
                    for (Map.Entry<String, String> item : configMap.entrySet()) {
                        liquibase.setChangeLogParameter(item.getKey(), item.getValue());
                    }
                    liquibase.update(new Contexts(), new LabelExpression());
                }
            } else {
                throw new RuntimeException("Connection error");
            }

//            CommandScope updateCommand;
//            try (Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection))) {
////                final Liquibase liquibase = new Liquibase(configMap.get("changelog"), new FileSystemResourceAccessor(), database);
////                liquibase.calculateCheckSum(new Context(), new LabelExpression());
////                final Liquibase liquibase = new Liquibase(configMap.get("changelog"), new CompositeResourceAccessor(new ClassLoaderResourceAccessor()).addResourceAccessor())
//                updateCommand = new CommandScope(UpdateCommandStep.COMMAND_NAME);
//                updateCommand.addArgumentValue(DbUrlConnectionArgumentsCommandStep.DATABASE_ARG, database);
//            }
//            updateCommand.addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, configMap.get("changelog"));
//            updateCommand.execute();
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


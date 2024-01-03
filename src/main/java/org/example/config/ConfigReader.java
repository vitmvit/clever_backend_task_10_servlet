package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
public class ConfigReader {

    @Autowired
    private Environment environment;

    @Bean
    public Map<String, String> getConfigMap() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("driver", environment.getProperty("driver"));
        configMap.put("url", environment.getProperty("url"));
        configMap.put("username", environment.getProperty("username"));
        configMap.put("password", environment.getProperty("password"));
        // Другие конфигурационные значения
        return configMap;
    }
}
//
//public class ConfigReader {
//
//    public Map<String, String> getConfigMap() {
//        try {
//            var properties = loadProperties();
//            Map<String, String> configMap = new HashMap<>(properties.size());
//            for (Map.Entry<Object, Object> item : properties.entrySet()) {
//                configMap.put((String) item.getKey(), (String) item.getValue());
//            }
//            return configMap;
//        } catch (IOException e) {
//            throw new ResourceNotFoundException(e);
//        }
//    }
//
//    private Properties loadProperties() throws IOException {
//        var properties = new Properties();
//        var inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(SOLUTION_CONFIG);
//        properties.load(inputStream);
//        inputStream.close();
//        return properties;
//    }
//}
package co.hadwen.web.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {
    @Value("datasource.url")
    String datasourceUrl;

}

package co.hadwen.web.hibernate;

import co.hadwen.hibernate.HibernateContext;
import co.hadwen.user.entity.UserEntity;
import lombok.NonNull;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class HibernateConfig {
    @Value("${spring.datasource.url}")
    String datasourceUrl;

    @Value("${spring.datasource.username}")
    String datasourceUsername;

    @Value("${spring.datasource.password}")
    String datasourcePassword;

    @Bean
    AnnotationConfiguration annotationConfig() {
        AnnotationConfiguration configuration = new AnnotationConfiguration()
                .addAnnotatedClass(UserEntity.class);
        configuration.setProperties(createProperties());
        return configuration.configure();
    }

    @Bean
    HibernateContext hibernateContext(@NonNull AnnotationConfiguration hibernateConfig) {
        return new HibernateContext(hibernateConfig.buildSessionFactory());
    }
    private Properties createProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.connection.url", datasourceUrl);
        properties.setProperty("hibernate.connection.password", datasourcePassword);
        properties.setProperty("hibernate.connection.username", datasourceUsername);
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("show_sql", "true");
        properties.setProperty("hbm2ddl.auto", "update");
        return properties;
    }
}

package co.hadwen.web.hibernate;

import co.hadwen.hibernate.HibernateSession;
import co.hadwen.hibernate.HibernateSessionFactory;
import co.hadwen.user.entity.UserAccount;
import co.hadwen.web.session.WebSessionToken;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class HibernateConfig {
    private static final String CONN_FORMAT = "jdbc:postgresql:%s:%s/sparkle";
    @Value("${spring.datasource.url}")
    String datasourceUrl;

    @Value("${spring.datasource.username}")
    String datasourceUsername;

    @Value("${spring.datasource.password}")
    String datasourcePassword;

    @Bean
    org.hibernate.cfg.Configuration annotationConfig() {

        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(WebSessionToken.class)
                .setProperties(createProperties());
    }

    @Bean
    HibernateSessionFactory hibernateSessionFactory(@NonNull org.hibernate.cfg.Configuration hibernateConfig) {
        return new HibernateSessionFactory(hibernateConfig);
    }

    @Bean
    HibernateSession hibernateSession(@NonNull HibernateSessionFactory sessionFactory) {
        return sessionFactory.create();
    }

    private Properties createProperties() {
        String port = getEnvVariableWithDefault("DB_PORT", "5432");
        String hostUrl = getEnvVariableWithDefault("DB_HOST", "//localhost");
        String connUrl = String.format(CONN_FORMAT, hostUrl, port);
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.connection.url", connUrl);
        properties.setProperty("hibernate.connection.password", "sEEkVtKK3#s\\n+%b");
        properties.setProperty("hibernate.connection.username", datasourceUsername);
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("show_sql", "true");
        properties.setProperty("hbm2ddl.auto", "update");
        return properties;
    }

    private static String getEnvVariableWithDefault(@NonNull String envVariable, @NonNull String defaultValue) {
        String value = System.getenv(envVariable);
        if(StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }
}

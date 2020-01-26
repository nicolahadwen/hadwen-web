package co.hadwen.web.hibernate;

import co.hadwen.hibernate.HibernateSession;
import co.hadwen.hibernate.HibernateSessionFactory;
import co.hadwen.user.entity.UserAccount;
import co.hadwen.web.system.EnvironmentVariables;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.logging.Logger;

@Configuration
public class HibernateConfig {
    private static final long SLEEP_TIME = 2000;
    private static final int MAX_RETRIES = 12;
    private static Logger logger = Logger.getLogger(HibernateConfig.class.getName());

    private static final String CONN_FORMAT = "jdbc:postgresql://%s:%s/sparkle";

    @Bean
    org.hibernate.cfg.Configuration annotationConfig() {

        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(UserAccount.class)
                //.addAnnotatedClass(WebSessionToken.class)
                .setProperties(createProperties());
    }

    @Bean
    HibernateSessionFactory hibernateSessionFactory() {
        for (int i = 0; i < MAX_RETRIES; i++) {
            try {
                org.hibernate.cfg.Configuration hibernateConfig = new org.hibernate.cfg.Configuration()
                        .addAnnotatedClass(UserAccount.class)
                        //.addAnnotatedClass(WebSessionToken.class)
                        .setProperties(createProperties());
                return new HibernateSessionFactory(hibernateConfig);
            } catch (ExceptionInInitializerError | Exception e) {
                try {
                    Thread.sleep(SLEEP_TIME);
                    System.out.printf("Sleeping: %d\n", i);
                } catch (Exception e2) {
                    System.out.println("Sleep exception: " + e.getMessage());
                }
                System.out.println(e.getMessage());
            }
        }
        org.hibernate.cfg.Configuration hibernateConfig = new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(UserAccount.class)
                //.addAnnotatedClass(WebSessionToken.class)
                .setProperties(createProperties());
        return new HibernateSessionFactory(hibernateConfig);
    }

    @Bean
    HibernateSession hibernateSession(@NonNull HibernateSessionFactory sessionFactory) {
        return sessionFactory.create();
    }

    private Properties createProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.connection.url", createConnUrl());
        properties.setProperty("hibernate.connection.username",
                System.getenv(EnvironmentVariables.DB_USERNAME.getValue()));
        properties.setProperty(
                "hibernate.connection.password",
                System.getenv(EnvironmentVariables.DB_PASSWORD.getValue()));
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("show_sql", "true");
        properties.setProperty("hbm2ddl.auto", "update");
        properties.keySet().forEach(key -> {
            System.out.printf("*Created with Property: %s=%s\n", key.toString(), properties.getProperty(key.toString()));
        });
        return properties;
    }

    private static String createConnUrl() {
        return String.format(
                CONN_FORMAT,
                System.getenv(EnvironmentVariables.DB_HOST.getValue()),
                System.getenv(EnvironmentVariables.DB_PORT.getValue()));
    }
}

package co.hadwen.web.session;

import co.hadwen.hibernate.HibernateSession;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSessionConfig {
    @Bean
    WebSessionClient sessionClient(@NonNull HibernateSession hibernateSession) {
        return new WebSessionClient(hibernateSession);
    }
}

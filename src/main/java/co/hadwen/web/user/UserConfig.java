package co.hadwen.web.user;

import co.hadwen.hibernate.HibernateSession;
import co.hadwen.user.UserClient;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    UserClient userClient(@NonNull HibernateSession hibernateSession) {
        return new UserClient(hibernateSession.getSession());
    }
}

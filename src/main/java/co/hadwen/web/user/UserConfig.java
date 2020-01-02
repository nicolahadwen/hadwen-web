package co.hadwen.web.user;

import co.hadwen.hibernate.HibernateContext;
import co.hadwen.user.UserClient;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    UserClient userClient(@NonNull HibernateContext hibernateContext) {
        return new UserClient(hibernateContext);
    }
}

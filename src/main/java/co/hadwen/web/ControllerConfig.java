package co.hadwen.web;

import co.hadwen.web.auth.LoginConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        LoginConfig.class
})
public class ControllerConfig {

}

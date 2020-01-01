package co.hadwen.web.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String FRONTEND_LOCAL_HOST = "http://localhost:8081";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfig = new CorsConfiguration();
        // todo: setup for prod, make env variable and based on env
        corsConfig.setAllowedOrigins(Collections.singletonList(FRONTEND_LOCAL_HOST));
        corsConfig.setAllowedMethods(
                Arrays.asList(
                        RequestMethod.GET.name(),
                        RequestMethod.POST.name(),
                        RequestMethod.DELETE.name(),
                        RequestMethod.OPTIONS.name(),
                        RequestMethod.PATCH.name()));
        corsConfig.addAllowedHeader("*");
        corsConfig.addExposedHeader("location");
        corsConfig.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        http.cors().configurationSource(source)
                .and()
                //todo: figure out what this is.
                .csrf()
                .disable();
    }
}

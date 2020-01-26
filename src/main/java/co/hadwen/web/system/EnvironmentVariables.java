package co.hadwen.web.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  EnvironmentVariables {
    DB_PORT("DB_PORT"),
    DB_HOST("DB_HOST"),
    DB_USERNAME("DB_USERNAME"),
    DB_PASSWORD("DB_PASSWORD");
    private String value;

}

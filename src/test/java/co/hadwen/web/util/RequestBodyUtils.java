package co.hadwen.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestBodyUtils {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package co.hadwen.web.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OAuthCredentialsDTO {
    private String clientId;
    private String clientSecret;

}

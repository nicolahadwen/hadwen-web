package co.hadwen.web.auth;

import co.hadwen.web.auth.dto.OAuthCredentialsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("v1")
@RestController
public class OAuthController {
    @Value("security.oauth2.client.client-id")
    private String clientId;

    @Value("security.oauth2.client.client-secret")
    private String clientSecret;

    @GetMapping("/oauth/credentials")
    ResponseEntity<OAuthCredentialsDTO> getCredentials() {
        return ResponseEntity.ok(OAuthCredentialsDTO.builder()
                .clientId(clientId)
                .build());
    }
}

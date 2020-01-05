package co.hadwen.web.auth;

import co.hadwen.user.UserClient;
import co.hadwen.user.entity.UserAccount;
import co.hadwen.web.auth.dto.LoginDTO;
import co.hadwen.web.exception.NotFoundException;
import co.hadwen.web.exception.NotFoundException.Entity;
import co.hadwen.web.exception.ValidationException;
import co.hadwen.web.exception.dto.InvalidValueDTO;
import co.hadwen.web.exception.dto.InvalidValueDTO.ErrorType;
import co.hadwen.web.exception.dto.ValidationErrorDTO;
import co.hadwen.web.session.WebSessionClient;
import co.hadwen.web.session.WebSessionToken;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllArgsConstructor
@RequestMapping("v1")
@RestController
public class LoginController {
    private final UserClient userClient;
    private final WebSessionClient webSessionClient;

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody LoginDTO request) throws Exception {
        UserAccount user =  userClient.getByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException(Entity.USER, request.getEmail()));
        System.out.println("password request: " + request.getPassword());

        if(!user.doesPasswordMatch(request.getPassword())) {
            throw new ValidationException(
                    ValidationErrorDTO.builder()
                            .invalidValues(InvalidValueDTO.builder()
                                    .field("password")
                                    .type(ErrorType.INVALID)
                                    .build())
                            .build()
            );
        }
        WebSessionToken sessionToken = webSessionClient.create(user.getUserId());
        return ResponseEntity.created(new URI(sessionToken.getSessionToken())).build();
    }
}

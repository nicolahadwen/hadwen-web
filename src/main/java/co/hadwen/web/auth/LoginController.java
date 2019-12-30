package co.hadwen.web.auth;

import co.hadwen.web.auth.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("v1")
@RestController
public class LoginController {
    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody LoginDTO request) throws Exception {
        return ResponseEntity.created(new URI("session-token")).build();
    }
}

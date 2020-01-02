package co.hadwen.web.user;

import co.hadwen.user.UserClient;
import co.hadwen.user.entity.UserEntity;
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
public class UserController {
    private UserClient userClient;

    @PostMapping("/user")
    ResponseEntity<String> createUser(@RequestBody UserEntity user) throws Exception {
        userClient.createUser(user);
        return ResponseEntity.created(new URI("session-token")).build();
    }
}

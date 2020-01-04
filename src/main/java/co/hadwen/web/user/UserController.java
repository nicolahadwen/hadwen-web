package co.hadwen.web.user;

import co.hadwen.user.UserClient;
import co.hadwen.user.entity.UserEntity;
import co.hadwen.web.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllArgsConstructor
@RequestMapping("v1")
@RestController
public class UserController {
    private static final String CREATED_URI_FORMAT = "user/%s";
    private UserClient userClient;

    @GetMapping("/user/{id}")
    ResponseEntity<UserEntity> get(@PathVariable String id) throws Exception {
        return userClient.get(id).map(user -> ResponseEntity.ok().body(user))
                .orElseThrow(() -> new NotFoundException("user", "id=" + id));
    }

    @GetMapping("/user/search")
    ResponseEntity<UserEntity> search(
            @RequestParam SearchType type,
            @RequestParam String query) throws Exception {
        if(type == SearchType.EMAIL) {
            return userClient.getByEmail(query)
                    .map(user -> ResponseEntity.ok().body(user))
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping("/user")
    ResponseEntity<String> create(@RequestBody UserEntity user) throws Exception {
        userClient.create(user);
        return ResponseEntity.created(new URI(String.format(CREATED_URI_FORMAT, user.getUserId()))).build();
    }

    @PatchMapping("/user/{id}")
    ResponseEntity<String> update(
            @PathVariable String id,
            @RequestBody UserEntity update) throws Exception {
        UserEntity user = userClient.get(id)
                .orElseThrow(() -> new NotFoundException("user", "id=" + id));
        if(update.getEmail() != null && !StringUtils.isEmpty(update.getEmail())) {
            user.setEmail(update.getEmail());
        }
        if(update.getPassword() != null && !StringUtils.isEmpty(update.getPassword())) {
            user.setPassword(update.getPassword());
        }
        if(update.getFirstName() != null && !StringUtils.isEmpty(update.getFirstName())) {
            user.setFirstName(update.getFirstName());
        }
        if(update.getLastName() != null && !StringUtils.isEmpty(update.getLastName())) {
            user.setLastName(update.getLastName());
        }
        userClient.update(user);
        return ResponseEntity.noContent().build();
    }
}

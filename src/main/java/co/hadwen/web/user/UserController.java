package co.hadwen.web.user;

import co.hadwen.user.UserClient;
import co.hadwen.user.entity.UserAccount;
import co.hadwen.web.exception.NotFoundException;
import co.hadwen.web.exception.NotFoundException.Entity;
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
    @GetMapping("/user/test")
    ResponseEntity<String> search() throws Exception {
        return ResponseEntity.ok("Hello!!!!!");
    }

    /*
    private static final String CREATED_URI_FORMAT = "user/%s";
    private UserClient userClient;

    @GetMapping("/user/{id}")
    ResponseEntity<UserAccount> get(@PathVariable String id) throws Exception {
        return userClient.get(id).map(user -> ResponseEntity.ok().body(user))
                .orElseThrow(() -> new NotFoundException(Entity.USER, "id=" + id));
    }

    @GetMapping("/user/search")
    ResponseEntity<UserAccount> search(
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
    ResponseEntity<String> create(@RequestBody UserAccount user) throws Exception {
        userClient.create(user);
        return ResponseEntity.created(new URI(String.format(CREATED_URI_FORMAT, user.getUserId()))).build();
    }

    @PatchMapping("/user/{id}")
    ResponseEntity<String> update(@PathVariable String id, @RequestBody UserAccount update) {
        UserAccount user = userClient.get(id)
                .orElseThrow(() -> new NotFoundException(Entity.USER, "id=" + id));
        if(update.getEmail() != null && !StringUtils.isEmpty(update.getEmail())) {
            user.setEmail(update.getEmail());
        }
        if(update.getPassword() != null && !StringUtils.isEmpty(update.getPassword())) {
            System.out.println("password update: " + update.getPassword());
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
    }*/
}

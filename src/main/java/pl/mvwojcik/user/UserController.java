package pl.mvwojcik.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity getUser(@PathVariable String username, Authentication auth) {
        return userService.getUser(username,auth).toResponseEntity();
    }

//    @PostMapping()
//    public ResponseEntity registerUser(@RequestBody UserRegistrationDTO userDTO) {
//
//    }
}

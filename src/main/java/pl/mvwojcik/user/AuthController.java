package pl.mvwojcik.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.registerUser(userRegistrationDTO).toResponseEntity();
    }

}

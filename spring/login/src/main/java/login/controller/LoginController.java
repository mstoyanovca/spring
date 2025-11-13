package login.controller;

import jakarta.validation.Valid;
import login.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(@Valid User user) {
        return "JWT token";
    }
}

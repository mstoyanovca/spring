package login.controller;

import jakarta.validation.Valid;
import login.entity.LoginUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(@Valid LoginUser loginUser) {
        return "JWT token";
    }
}

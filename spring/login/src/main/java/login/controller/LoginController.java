package login.controller;

import jakarta.validation.Valid;
import login.entity.LoginUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public ResponseEntity<LoginUser> login(@RequestBody @Valid LoginUser user) {
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // POST -   create - HttpStatus.CREATED
    // GET -    read   - HttpStatus.OK / HttpStatus.NOT_FOUND
    // PUT -    update - HttpStatus.OK / HttpStatus.NOT_FOUND
    // DELETE - delete - HttpStatus.NO_CONTENT / HttpStatus.NOT_FOUND
}

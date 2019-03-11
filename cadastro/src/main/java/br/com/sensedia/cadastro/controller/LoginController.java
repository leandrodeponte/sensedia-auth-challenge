package br.com.sensedia.cadastro.controller;

import br.com.sensedia.cadastro.model.User;
import br.com.sensedia.cadastro.service.LoginService;
import br.com.sensedia.cadastro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;

@RestController
@CrossOrigin
@RequestMapping(path="/api")
public class LoginController {


    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @GetMapping("/login/{username}/{auth-key}")
    public ResponseEntity<User> get(
            @PathVariable("username") String username
            ,@PathVariable("auth-key") String authenticationKey) {

        User user = null;

        try {
            user = loginService.authenticate(username, authenticationKey);
        } catch (InvalidKeyException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);

    }


}



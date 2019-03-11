package br.com.sensedia.cadastro.controller;

import br.com.sensedia.StepsAuthenticator.model.AuthenticationKey;
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
public class AuthKeyController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;


    @GetMapping("/auth/{username}")
    public ResponseEntity<AuthenticationKey> get(
            @PathVariable("username") String username) {

        User user = userService.findByUsername(username);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AuthenticationKey authenticationKey = null;

        try {
            authenticationKey = loginService.generateAuthenticationKey(user);
        } catch (InvalidKeyException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<AuthenticationKey>(authenticationKey, HttpStatus.OK);

    }
}

package br.com.sensedia.cadastro.controller;

import br.com.sensedia.cadastro.model.User;
import br.com.sensedia.cadastro.service.UserRepository;
import br.com.sensedia.cadastro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> get(
            @PathVariable("id") Long id) {

        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    @PostMapping("/user/")
    public ResponseEntity<User> post(
            @RequestBody User user) {

        userService.create(user);

        return new ResponseEntity<User>(user, HttpStatus.OK);

    }


}

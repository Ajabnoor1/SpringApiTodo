package com.rest_api.controller;

import com.rest_api.service.User;
import com.rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody User newUser){

        //generate Key
        newUser.setSecret(UUID.randomUUID().toString());
      var savedUser =  userRepository.save(newUser);
        return new ResponseEntity<User>(savedUser , HttpStatus.CREATED);
    }
    @GetMapping("/user")
    private ResponseEntity<User> user(@RequestParam(value = "id") int id){
        var user =  userRepository.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity("User is not Excise    " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/validate")
    private ResponseEntity<String> validate(@RequestParam(value = "email") String email,
                                          @RequestParam(value = "password") String password){
        var validUser =  userRepository.findByEmailAndPassword(email, password);
        if(validUser.isPresent()){
            return new ResponseEntity<String>("Api Secret     " + validUser.get().getSecret(), HttpStatus.OK);
        }
        return new ResponseEntity("No Account found   " ,  HttpStatus.NOT_FOUND);
    }
}

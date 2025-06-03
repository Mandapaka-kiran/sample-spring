package com.sample.controller;

import com.sample.entity.User;
import com.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public ResponseEntity test(){
        return ResponseEntity.status(HttpStatus.FOUND).body("Welcome");
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity getUserByPathVariable(@PathVariable("id") int id){
            User user= userService.getUSerById(id);
        return

                ResponseEntity.status(HttpStatus.FOUND).body(user);
    }
    //conflict 2
    //conflict 4
}

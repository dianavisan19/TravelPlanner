package com.example.travelplanner.controller;

import com.example.travelplanner.entity.User;
import com.example.travelplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        System.out.println("User save called...");
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }



}

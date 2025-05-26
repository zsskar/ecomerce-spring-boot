package com.example.demo.user.controller;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

     @PostMapping("/create")
     public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO user) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         User savedUser = userService.registerUser(user);
         return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
     }

     @PostMapping("/login")
     public ResponseEntity<String> loginUser(@Valid @RequestBody UserDTO user) {
         String token = userService.loginUser(user);
         return ResponseEntity.ok(token);
     }

     @PostMapping("/logout")
     public ResponseEntity<String> logoutUser(@Valid @RequestBody UserDTO user) {
         userService.logoutUser(user);
         return ResponseEntity.ok("User logged out successfully");
     }
}

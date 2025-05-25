package com.example.demo.user.controller;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

     @PostMapping("/create")
     public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO user) {
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

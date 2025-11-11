package com.sampleLogin.userlogin.controller;

import com.sampleLogin.userlogin.dtos.CreateUserRequest;
import com.sampleLogin.userlogin.dtos.LoginRequest;
import com.sampleLogin.userlogin.entity.User;
import com.sampleLogin.userlogin.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        if(req.getUsername().isBlank() || req.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Enter Valid Credentials"));
        }
        if(!userService.existsByUsername(req.getUsername())) {
            return ResponseEntity.status(404).body(Map.of("error", "User does not exists"));
        }
        boolean ok = userService.validateCredentials(req.getUsername(), req.getPassword());
        if(!ok) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
        User user = userService.getByUsername(req.getUsername());
        if(user == null) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest req) {
        try {
            User created = userService.createUser(req);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        User u = userService.getByUsername(username);
        if(u == null) return ResponseEntity.status(404).body("User does not exist");
        return ResponseEntity.ok(u);
    }
}

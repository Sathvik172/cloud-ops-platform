package com.cloudops.auth.controller;

import com.cloudops.auth.dto.LoginRequest;
import com.cloudops.auth.dto.RegisterRequest;
import com.cloudops.auth.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request) {

        String message = authService.register(request);

        return ResponseEntity.ok(
                Map.of("message", message)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        String token = authService.login(request);

        return ResponseEntity.ok(
                Map.of("token", token)
        );
    }
}

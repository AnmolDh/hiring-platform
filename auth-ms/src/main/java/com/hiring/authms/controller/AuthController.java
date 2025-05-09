package com.hiring.authms.controller;

import com.hiring.authms.domain.dto.AuthRequest;
import com.hiring.authms.domain.dto.AuthResponse;
import com.hiring.authms.domain.dto.RegisterRequest;
import com.hiring.authms.domain.dto.RegisterResponse;
import com.hiring.authms.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid final AuthRequest authRequest) {
        AuthResponse authResponse = authService.login(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid final RegisterRequest registerRequest) {
        RegisterResponse registerResponse = authService.register(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }
}

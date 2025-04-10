package com.techkieventures.adminservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techkieventures.adminservice.service.AuthService;
import com.techkieventures.adminservice.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username) {
        String token = jwtUtil.generateToken(username);
        authService.storeToken(username, token);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String username, @RequestParam String token) {
        boolean isValid = authService.validateToken(username, token);
        return isValid ? ResponseEntity.ok("Valid Token") : ResponseEntity.status(401).body("Invalid Token");
    }
}

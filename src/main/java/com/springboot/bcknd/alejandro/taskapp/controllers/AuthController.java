package com.springboot.bcknd.alejandro.taskapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Usa request.username() y request.password() en lugar de getters
        if ("admin".equals(request.username()) && "password123".equals(request.password())) {
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer VALIDO-TOKEN")
                    .body("{\"message\": \"Login exitoso\"}");
        }
        return ResponseEntity.status(401).body("{\"error\": \"Credenciales inválidas\"}");
    }

    record LoginRequest(String username, String password) {
    }
}

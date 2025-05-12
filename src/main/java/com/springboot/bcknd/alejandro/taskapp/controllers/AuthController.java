package com.springboot.bcknd.alejandro.taskapp.controllers;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        String validUsername = "admin";
        String validPassword = "1234";

        if (validUsername.equals(request.getUsername()) &&
                validPassword.equals(request.getPassword())) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer VALIDO-TOKEN");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body("Login exitoso");
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    @Getter
    public static class AuthRequest {
        // Getters y setters obligatorios
        private String username;
        private String password;

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
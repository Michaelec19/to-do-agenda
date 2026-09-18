package com.agendaone.todoagenda.api.controllers;

import com.agendaone.todoagenda.domain.UserExternal;
import com.agendaone.todoagenda.infrastructure.repositories.UserExternalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserExternalRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserExternalRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String rawPassword = credentials.get("password");

        Optional<UserExternal> userOpt = userRepository.findByEmailUser(email);

        if (userOpt.isPresent()) {
            UserExternal user = userOpt.get();

            if (passwordEncoder.matches(rawPassword, user.getPasswordUser())) {

                if (user.getIdRol() != null && user.getIdRol() == 1L) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("token", "dummy-jwt-para-frontend");

                    Map<String, Object> userData = new HashMap<>();
                    userData.put("id", user.getIdUser());
                    userData.put("nombre", user.getNameUser() != null ? user.getNameUser() : "Profesor");
                    userData.put("apellido", user.getLastNameUser() != null ? user.getLastNameUser() : "");
                    userData.put("email", user.getEmailUser());
                    userData.put("role", "ADMIN");

                    response.put("user", userData);
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                            .body(Map.of("message", "Acceso denegado: Solo el profesor (ADMIN) puede usar esta agenda."));
                }
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Credenciales incorrectas"));
    }
}
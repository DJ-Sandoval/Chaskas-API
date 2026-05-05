package com.api.BackChaskas.view.controller;

import com.api.BackChaskas.service.auth.AuthService;
import com.api.BackChaskas.view.dto.auth.AuthResponseDto;
import com.api.BackChaskas.view.dto.auth.LoginRequestDto;
import com.api.BackChaskas.view.dto.auth.RegisterRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Endpoints para login y registro de usuarios")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Autentica un usuario y devuelve un token JWT")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        AuthResponseDto response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar cliente", description = "Registra un nuevo usuario con rol de cliente")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequest) {
        AuthResponseDto response = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register/employee")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Registrar empleado", description = "Registra un nuevo empleado/cajero (solo admin)")
    public ResponseEntity<AuthResponseDto> registerEmployee(
            @Valid @RequestBody RegisterRequestDto registerRequest) {
        AuthResponseDto response = authService.registerEmployee(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/logout")
    @Operation(summary = "Cerrar sesión", description = "Invalida el token JWT actual")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        authService.logout(bearerToken);
        return ResponseEntity.noContent().build();
    }
}

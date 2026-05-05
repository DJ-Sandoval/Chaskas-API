package com.api.BackChaskas.service.auth;

import com.api.BackChaskas.config.security.JwtTokenProvider;
import com.api.BackChaskas.mapper.UserMapper;
import com.api.BackChaskas.model.entity.Role;
import com.api.BackChaskas.model.entity.User;
import com.api.BackChaskas.model.repository.BlacklistedTokenRepository;
import com.api.BackChaskas.model.repository.RoleRepository;
import com.api.BackChaskas.model.repository.UserRepository;
import com.api.BackChaskas.service.exception.AuthenticationException;
import com.api.BackChaskas.service.exception.UserAlreadyExistsException;
import com.api.BackChaskas.view.dto.auth.AuthResponseDto;
import com.api.BackChaskas.view.dto.auth.LoginRequestDto;
import com.api.BackChaskas.view.dto.auth.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequest) {
        try {
            // Verificar si el usuario existe
            User user = userRepository.findByEmail(loginRequest.getEmail().toLowerCase())
                    .orElseThrow(() -> new AuthenticationException(
                            "El email " + loginRequest.getEmail() + " no está registrado"));

            // Verificar si la cuenta está activa
            if (!user.getActive()) {
                throw new AuthenticationException("La cuenta está desactivada. Contacte al administrador.");
            }

            // Autenticar credenciales
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail().toLowerCase(),
                            loginRequest.getPassword()
                    )
            );

            // Generar token JWT
            String token = jwtTokenProvider.generateToken(authentication);
            Long expiresIn = jwtTokenProvider.getExpirationTime();

            log.info("Usuario {} autenticado exitosamente", user.getEmail());
            return userMapper.toAuthResponse(user, token, expiresIn);

        } catch (BadCredentialsException e) {
            log.warn("Intento de login fallido para email: {}", loginRequest.getEmail());
            throw new AuthenticationException("Credenciales inválidas. Verifique su email y contraseña.");
        }
    }

    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto registerRequest) {
        // Validar que el email no exista
        if (userRepository.existsByEmail(registerRequest.getEmail().toLowerCase())) {
            throw new UserAlreadyExistsException(
                    "El email " + registerRequest.getEmail() + " ya está registrado");
        }

        // Validar que el username no exista
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UserAlreadyExistsException(
                    "El nombre de usuario " + registerRequest.getUsername() + " ya está en uso");
        }

        // Asignar rol por defecto (CLIENTE) o el especificado
        String roleName = registerRequest.getRoleName() != null ?
                registerRequest.getRoleName() : "ROLE_CUSTOMER";

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName));

        // Crear nuevo usuario
        User user = userMapper.toUser(registerRequest);
        user.setRole(role);
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));

        // Guardar usuario
        User savedUser = userRepository.save(user);

        log.info("Nuevo usuario registrado: {} con rol: {}", savedUser.getEmail(), roleName);

        // Generar token JWT para el nuevo usuario
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),
                null,
                role.getName().startsWith("ROLE_") ?
                        java.util.Collections.singletonList(() -> role.getName()) :
                        java.util.Collections.singletonList(() -> "ROLE_" + role.getName())
        );

        String token = jwtTokenProvider.generateToken(authentication);
        Long expiresIn = jwtTokenProvider.getExpirationTime();

        return userMapper.toAuthResponse(savedUser, token, expiresIn);
    }

    @Override
    @Transactional
    public AuthResponseDto registerEmployee(RegisterRequestDto registerRequest) {
        // Solo permitir registro de empleados/cajeros
        if (registerRequest.getRoleName() == null ||
                (!registerRequest.getRoleName().equals("ROLE_EMPLOYEE") &&
                        !registerRequest.getRoleName().equals("ROLE_ADMIN"))) {
            registerRequest.setRoleName("ROLE_EMPLOYEE");
        }

        return register(registerRequest);
    }

    @Override
    @Transactional
    public void logout(String token) {
        // Agregar token a la lista negra
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);

            // Verificar si el token ya está en lista negra
            if (!blacklistedTokenRepository.existsByToken(jwtToken)) {
                try {
                    // Extraer información del token
                    String email = jwtTokenProvider.getUsernameFromToken(jwtToken);
                    User user = userRepository.findByEmail(email).orElse(null);

                    // Crear entrada en lista negra
                    com.api.BackChaskas.model.entity.BlacklistedToken blacklistedToken =
                            com.api.BackChaskas.model.entity.BlacklistedToken.builder()
                                    .token(jwtToken)
                                    .blacklistedAt(java.time.LocalDateTime.now())
                                    .expiresAt(java.time.LocalDateTime.now().plusDays(1)) // Tiempo razonable
                                    .user(user)
                                    .build();

                    blacklistedTokenRepository.save(blacklistedToken);
                    log.info("Token agregado a lista negra para usuario: {}", email);
                } catch (Exception e) {
                    log.warn("Error al procesar logout: {}", e.getMessage());
                }
            }
        }
    }
}

package com.api.BackChaskas.service.impl;

import com.api.BackChaskas.config.jwt.JwtService;
import com.api.BackChaskas.model.entity.seguridad.Permiso;
import com.api.BackChaskas.model.entity.seguridad.Rol;
import com.api.BackChaskas.model.entity.seguridad.Usuario;
import com.api.BackChaskas.model.entity.sucursales.Sucursal;
import com.api.BackChaskas.model.repository.RolRepository;
import com.api.BackChaskas.model.repository.SucursalRepository;
import com.api.BackChaskas.model.repository.UsuarioRepository;
import com.api.BackChaskas.service.exception.DuplicateResourceException;
import com.api.BackChaskas.service.exception.ResourceNotFoundException;
import com.api.BackChaskas.service.exception.UnauthorizedException;
import com.api.BackChaskas.service.interfaces.AuthService;
import com.api.BackChaskas.service.interfaces.LogService;
import com.api.BackChaskas.view.dto.request.LoginRequestDTO;
import com.api.BackChaskas.view.dto.request.RegistroUsuarioRequestDTO;
import com.api.BackChaskas.view.dto.response.AuthResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final SucursalRepository sucursalRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LogService logService;
    private final HttpServletRequest request;

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        String ipAddress = request.getRemoteAddr();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

            if (!usuario.getActivo()) {
                throw new UnauthorizedException("Usuario inactivo. Contacte al administrador.");
            }

            String token = jwtService.generateToken(userDetails);

            logService.registrarLogExitoso(
                    usuario.getEmail(),
                    "LOGIN",
                    "AUTH",
                    ipAddress
            );

            return buildAuthResponse(token, usuario);

        } catch (BadCredentialsException e) {
            logService.registrarLogError(
                    loginRequest.getEmail(),
                    "LOGIN_FAILED",
                    "AUTH",
                    "Credenciales inválidas: " + e.getMessage(),
                    ipAddress
            );
            throw new BadCredentialsException("Email o contraseña incorrectos");
        }
    }

    @Override
    @Transactional
    public AuthResponseDTO register(RegistroUsuarioRequestDTO registerRequest) {
        if (usuarioRepository.existsByEmail(registerRequest.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }

        Rol rol = rolRepository.findById(registerRequest.getRolId() != null ? registerRequest.getRolId() : 2L)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setUuid(UUID.randomUUID());
        usuario.setNombre(registerRequest.getNombre());
        usuario.setApellido(registerRequest.getApellido());
        usuario.setEmail(registerRequest.getEmail());
        usuario.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        usuario.setRol(rol);
        usuario.setActivo(true);

        if (registerRequest.getSucursalId() != null) {
            Sucursal sucursal = sucursalRepository.findById(registerRequest.getSucursalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada"));
            usuario.setSucursal(sucursal);
        }

        Usuario savedUsuario = usuarioRepository.save(usuario);

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(savedUsuario.getEmail())
                .password(savedUsuario.getPasswordHash())
                .authorities(rol.getNombre())
                .build();

        String token = jwtService.generateToken(userDetails);

        logService.registrarLogExitoso(
                savedUsuario.getEmail(),
                "REGISTER",
                "AUTH",
                "Usuario registrado exitosamente"
        );

        return buildAuthResponse(token, savedUsuario);
    }

    @Override
    public AuthResponseDTO getCurrentUser(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return buildAuthResponse(null, usuario);
    }

    @Override
    public void logout(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String email = jwtService.extractUsername(token);

            logService.registrarLogExitoso(
                    email,
                    "LOGOUT",
                    "AUTH",
                    "Usuario cerró sesión"
            );
        }
    }

    private AuthResponseDTO buildAuthResponse(String token, Usuario usuario) {
        Set<String> permisos = usuario.getRol().getPermisos().stream()
                .map(Permiso::getClave)
                .collect(Collectors.toSet());

        return AuthResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .id(usuario.getId())
                .nombre(usuario.getNombre() + " " + (usuario.getApellido() != null ? usuario.getApellido() : ""))
                .email(usuario.getEmail())
                .rol(usuario.getRol().getNombre())
                .permisos(permisos)
                .sucursalId(usuario.getSucursal() != null ? usuario.getSucursal().getId() : null)
                .build();
    }
}


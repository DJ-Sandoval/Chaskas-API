package com.api.BackChaskas.config.security;

import com.api.BackChaskas.model.entity.User;
import com.api.BackChaskas.model.repository.BlacklistedTokenRepository;
import com.api.BackChaskas.model.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                // Verificar si el token está en lista negra
                if (blacklistedTokenRepository.existsByToken(jwt)) {
                    log.warn("Token en lista negra detectado");
                    filterChain.doFilter(request, response);
                    return;
                }

                String username = tokenProvider.getUsernameFromToken(jwt);
                Optional<User> userOptional = userRepository.findByEmail(username);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();

                    if (user.getActive()) {
                        // Crear authorities basados en el rol del usuario
                        List<SimpleGrantedAuthority> authorities =
                                Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName()));

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(user, null, authorities);
                        authentication.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        log.debug("Autenticación establecida para usuario: {}", username);
                    } else {
                        log.warn("Usuario inactivo intentó acceder: {}", username);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("No se pudo establecer la autenticación del usuario en el contexto de seguridad", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

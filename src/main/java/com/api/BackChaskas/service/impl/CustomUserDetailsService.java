package com.api.BackChaskas.service.impl;

import com.api.BackChaskas.model.entity.seguridad.Usuario;
import com.api.BackChaskas.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmailWithPermissions(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        if (!usuario.getActivo()) {
            throw new UsernameNotFoundException("Usuario inactivo");
        }

        List<SimpleGrantedAuthority> authorities = usuario.getRol().getPermisos().stream()
                .map(permiso -> new SimpleGrantedAuthority(permiso.getClave()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()));

        return new User(
                usuario.getEmail(),
                usuario.getPasswordHash(),
                authorities
        );
    }
}

package com.api.BackChaskas.config.security;

import com.api.BackChaskas.model.entity.User;
import com.api.BackChaskas.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        if (!user.getActive()) {
            throw new UsernameNotFoundException("Usuario desactivado: " + email);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                user.getActive(),
                true,
                true,
                true,
                Collections.singletonList(
                        new SimpleGrantedAuthority(user.getRole().getName())
                )
        );
    }
}

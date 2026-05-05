package com.api.BackChaskas.service.user;


import com.api.BackChaskas.mapper.UserMapper;
import com.api.BackChaskas.model.entity.User;
import com.api.BackChaskas.model.repository.UserRepository;
import com.api.BackChaskas.service.exception.AuthenticationException;
import com.api.BackChaskas.view.dto.auth.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("Usuario no autenticado");
        }

        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Usuario no encontrado"));
    }

    @Override
    public AuthResponseDto getUserProfile() {
        User user = getCurrentUser();
        return userMapper.toAuthResponse(user, null, null);
    }

    @Override
    public boolean isAdmin() {
        return getCurrentUser().getRole().getName().equals("ROLE_ADMIN");
    }

    @Override
    public boolean isEmployee() {
        return getCurrentUser().getRole().getName().equals("ROLE_EMPLOYEE");
    }

    @Override
    public boolean isCustomer() {
        return getCurrentUser().getRole().getName().equals("ROLE_CUSTOMER");
    }
}

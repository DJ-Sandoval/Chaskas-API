package com.api.BackChaskas.mapper;

import com.api.BackChaskas.model.entity.User;
import com.api.BackChaskas.view.dto.auth.AuthResponseDto;
import com.api.BackChaskas.view.dto.auth.RegisterRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(RegisterRequestDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail().toLowerCase())
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .active(true)
                .build();
    }

    public AuthResponseDto toAuthResponse(User user, String token, Long expiresIn) {
        return AuthResponseDto.builder()
                .token(token)
                .tokenType("Bearer")
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().getName())
                .expiresIn(expiresIn)
                .build();
    }
}


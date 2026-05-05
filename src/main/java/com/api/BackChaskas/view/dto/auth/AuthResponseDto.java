package com.api.BackChaskas.view.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private String token;
    private String tokenType;
    private Long userId;
    private String username;
    private String email;
    private String fullName;
    private String role;
    private Long expiresIn;
}

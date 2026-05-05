package com.api.BackChaskas.service.auth;

import com.api.BackChaskas.view.dto.auth.AuthResponseDto;
import com.api.BackChaskas.view.dto.auth.LoginRequestDto;
import com.api.BackChaskas.view.dto.auth.RegisterRequestDto;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto loginRequest);
    AuthResponseDto register(RegisterRequestDto registerRequest);
    AuthResponseDto registerEmployee(RegisterRequestDto registerRequest);
    void logout(String token);
}

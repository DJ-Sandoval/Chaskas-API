package com.api.BackChaskas.service.interfaces;


import com.api.BackChaskas.view.dto.request.LoginRequestDTO;
import com.api.BackChaskas.view.dto.request.RegistroUsuarioRequestDTO;
import com.api.BackChaskas.view.dto.response.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(LoginRequestDTO loginRequest);
    AuthResponseDTO register(RegistroUsuarioRequestDTO registerRequest);
    AuthResponseDTO getCurrentUser(String email);
    void logout(String token);
}

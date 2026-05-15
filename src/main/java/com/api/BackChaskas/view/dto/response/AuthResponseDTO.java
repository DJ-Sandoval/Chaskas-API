package com.api.BackChaskas.view.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AuthResponseDTO {
    private String token;
    private String type;
    private Long id;
    private String nombre;
    private String email;
    private String rol;
    private Set<String> permisos;
    private Long sucursalId;
}

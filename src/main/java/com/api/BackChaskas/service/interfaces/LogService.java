package com.api.BackChaskas.service.interfaces;

import com.api.BackChaskas.model.entity.seguridad.Usuario;

public interface LogService {
    void registrarLog(Usuario usuario, String accion, String modulo, String descripcion, String ipAddress);
    void registrarLogExitoso(String email, String accion, String modulo, String ipAddress);
    void registrarLogError(String email, String accion, String modulo, String error, String ipAddress);
}

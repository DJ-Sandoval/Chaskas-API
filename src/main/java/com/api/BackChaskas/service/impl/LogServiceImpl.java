package com.api.BackChaskas.service.impl;

import com.api.BackChaskas.model.entity.auditoria.Log;
import com.api.BackChaskas.model.entity.seguridad.Usuario;
import com.api.BackChaskas.model.repository.LogRepository;
import com.api.BackChaskas.model.repository.UsuarioRepository;
import com.api.BackChaskas.service.interfaces.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void registrarLog(Usuario usuario, String accion, String modulo, String descripcion, String ipAddress) {
        Log log = new Log();
        log.setUsuario(usuario);
        log.setAccion(accion);
        log.setModulo(modulo);
        log.setDescripcion(descripcion);
        log.setIpAddress(ipAddress != null ? ipAddress : "0.0.0.0");
        logRepository.save(log);
    }

    @Override
    public void registrarLogExitoso(String email, String accion, String modulo, String ipAddress) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        usuario.ifPresent(u -> registrarLog(u, accion, modulo, "Éxito", ipAddress));
    }

    @Override
    public void registrarLogError(String email, String accion, String modulo, String error, String ipAddress) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        usuario.ifPresentOrElse(
                u -> registrarLog(u, accion, modulo, "Error: " + error, ipAddress),
                () -> {
                    Log log = new Log();
                    log.setAccion(accion);
                    log.setModulo(modulo);
                    log.setDescripcion("Error (usuario no encontrado): " + error);
                    log.setIpAddress(ipAddress != null ? ipAddress : "0.0.0.0");
                    logRepository.save(log);
                }
        );
    }
}

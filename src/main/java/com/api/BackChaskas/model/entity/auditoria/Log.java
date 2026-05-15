package com.api.BackChaskas.model.entity.auditoria;

import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.seguridad.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Log.java
@Entity
@Table(name = "logs")
@Getter
@Setter
public class Log extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(length = 100)
    private String accion;

    @Column(length = 100)
    private String modulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 100)
    private String ipAddress;
}

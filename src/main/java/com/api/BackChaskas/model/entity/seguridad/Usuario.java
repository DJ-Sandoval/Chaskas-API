package com.api.BackChaskas.model.entity.seguridad;

import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.sucursales.Sucursal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario extends BaseEntity {

    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(length = 150, unique = true, nullable = false)
    private String email;

    @Column(length = 255, nullable = false)
    private String passwordHash;

    private Boolean activo = true;
}

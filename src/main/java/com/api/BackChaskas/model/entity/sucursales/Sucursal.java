package com.api.BackChaskas.model.entity.sucursales;

import com.api.BackChaskas.model.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "sucursales")
@Getter
@Setter
public class Sucursal extends BaseEntity {

    private UUID uuid;

    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @Column(length = 30)
    private String telefono;

    private Boolean activa = true;
}

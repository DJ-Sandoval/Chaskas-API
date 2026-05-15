package com.api.BackChaskas.model.entity.seguridad;

import com.api.BackChaskas.model.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permisos")
@Getter
@Setter
public class Permiso extends BaseEntity {

    @Column(length = 100, nullable = false, unique = true)
    private String clave;

    @Column(length = 255)
    private String descripcion;
}
package com.api.BackChaskas.model.entity.proveedoresycompras;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
// Proveedor.java
@Entity
@Table(name = "proveedores")
@Getter @Setter
public class Proveedor extends BaseEntity {

    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 30)
    private String telefono;

    @Column(length = 150)
    private String email;

    @Column(length = 255)
    private String direccion;

    private Boolean activo = true;
}

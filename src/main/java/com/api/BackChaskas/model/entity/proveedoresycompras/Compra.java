package com.api.BackChaskas.model.entity.proveedoresycompras;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.seguridad.Usuario;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;

// Compra.java
@Entity
@Table(name = "compras")
@Getter @Setter
public class Compra extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Column(length = 50)
    private String estado; // PENDIENTE, RECIBIDA, CANCELADA
}

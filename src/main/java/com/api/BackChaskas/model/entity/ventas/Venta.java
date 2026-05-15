package com.api.BackChaskas.model.entity.ventas;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.cajas.Caja;
import com.api.BackChaskas.model.entity.seguridad.Usuario;
import com.api.BackChaskas.model.entity.sucursales.Sucursal;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

// Venta.java
@Entity
@Table(name = "ventas")
@Getter @Setter
public class Venta extends BaseEntity {

    private UUID uuid;

    @Column(length = 50, unique = true)
    private String folio;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "caja_id")
    private Caja caja;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal impuestos;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Column(length = 30)
    private String metodoPago;

    @Column(length = 30)
    private String estado;

    private Boolean offlineSync = false;
}

package com.api.BackChaskas.model.entity.cajas;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.seguridad.Usuario;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;

// MovimientoCaja.java
@Entity
@Table(name = "movimientos_caja")
@Getter @Setter
public class MovimientoCaja extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "caja_id")
    private Caja caja;

    @Column(length = 50)
    private String tipoMovimiento; // APERTURA, CIERRE, VENTA, RETIRO, etc.

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
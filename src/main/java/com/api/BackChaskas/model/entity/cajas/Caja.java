package com.api.BackChaskas.model.entity.cajas;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.seguridad.Usuario;
import com.api.BackChaskas.model.entity.sucursales.Sucursal;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Caja.java
@Entity
@Table(name = "cajas")
@Getter @Setter
public class Caja extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;

    @Column(precision = 10, scale = 2)
    private BigDecimal montoInicial;

    @Column(precision = 10, scale = 2)
    private BigDecimal montoFinal;

    @Column(length = 30)
    private String estado; // ABIERTA, CERRADA
}


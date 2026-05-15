package com.api.BackChaskas.model.entity.inventario;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.ingredientes.Ingrediente;
import com.api.BackChaskas.model.entity.seguridad.Usuario;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;

// MovimientoInventario.java
@Entity
@Table(name = "movimientos_inventario")
@Getter @Setter
public class MovimientoInventario extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @Column(length = 50)
    private String tipoMovimiento; // ENTRADA, SALIDA, AJUSTE, etc.

    @Column(precision = 10, scale = 2)
    private BigDecimal cantidad;

    private Long referenciaId; // ID de compra, venta, merma, etc.

    @Column(length = 255)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

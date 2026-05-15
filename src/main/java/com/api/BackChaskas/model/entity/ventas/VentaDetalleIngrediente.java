package com.api.BackChaskas.model.entity.ventas;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.ingredientes.Ingrediente;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// VentaDetalleIngrediente.java
@Entity
@Table(name = "venta_detalle_ingredientes")
@Getter @Setter
public class VentaDetalleIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "detalle_venta_id")
    private DetalleVenta detalleVenta;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @Column(precision = 10, scale = 2)
    private BigDecimal cantidad;

    private Boolean extra = false;
}

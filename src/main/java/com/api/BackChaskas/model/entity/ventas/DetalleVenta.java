package com.api.BackChaskas.model.entity.ventas;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.products.Producto;
import com.api.BackChaskas.model.entity.products.Tamano;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// DetalleVenta.java
@Entity
@Table(name = "detalle_ventas")
@Getter @Setter
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "tamano_id")
    private Tamano tamano;

    @Column(precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(length = 255)
    private String observaciones;
}

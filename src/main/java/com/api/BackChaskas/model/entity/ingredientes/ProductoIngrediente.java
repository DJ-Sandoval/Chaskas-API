package com.api.BackChaskas.model.entity.ingredientes;

import com.api.BackChaskas.model.entity.products.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

// ProductoIngrediente.java
@Entity
@Table(name = "productos_ingredientes")
@Getter
@Setter
public class ProductoIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal cantidad;

    private Boolean obligatorio = true;
}

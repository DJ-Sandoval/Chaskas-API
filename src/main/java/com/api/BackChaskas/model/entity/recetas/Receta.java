package com.api.BackChaskas.model.entity.recetas;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.products.Producto;
import com.api.BackChaskas.model.entity.products.Tamano;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

// Receta.java
@Entity
@Table(name = "recetas")
@Getter @Setter
public class Receta extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "tamano_id")
    private Tamano tamano;

    @Column(length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String instrucciones;

    @Column(precision = 10, scale = 2)
    private BigDecimal costoProduccion;

    private Boolean activa = true;
}
package com.api.BackChaskas.model.entity.recetas;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.ingredientes.Ingrediente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

// RecetaDetalle.java
@Entity
@Table(name = "recetas_detalle")
@Getter @Setter
public class RecetaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal cantidad;
}

package com.api.BackChaskas.model.entity.inventario;

import com.api.BackChaskas.model.entity.ingredientes.Ingrediente;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;

// Inventario.java
@Entity
@Table(name = "inventario")
@Getter @Setter
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;

    @Column(precision = 10, scale = 2)
    private BigDecimal stockActual;

    @Column(precision = 10, scale = 2)
    private BigDecimal stockMinimo;
}

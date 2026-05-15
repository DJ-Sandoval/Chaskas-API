package com.api.BackChaskas.model.entity.ingredientes;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

// Ingrediente.java
@Entity
@Table(name = "ingredientes")
@Getter @Setter
public class Ingrediente extends BaseEntity {

    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "tipo_ingrediente_id")
    private TipoIngrediente tipoIngrediente;

    @ManyToOne
    @JoinColumn(name = "unidad_medida_id")
    private UnidadMedida unidadMedida;

    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal stockActual;

    @Column(precision = 10, scale = 2)
    private BigDecimal stockMinimo;

    @Column(precision = 10, scale = 2)
    private BigDecimal costoUnitario;

    private Boolean activo = true;
}

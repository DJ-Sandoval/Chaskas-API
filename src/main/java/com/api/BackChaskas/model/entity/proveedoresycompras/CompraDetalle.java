package com.api.BackChaskas.model.entity.proveedoresycompras;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.ingredientes.Ingrediente;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;

// CompraDetalle.java
@Entity
@Table(name = "compras_detalle")
@Getter @Setter
public class CompraDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @Column(precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(precision = 10, scale = 2)
    private BigDecimal costoUnitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;
}

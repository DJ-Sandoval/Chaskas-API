package com.api.BackChaskas.model.entity.products;


import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.ingredientes.ProductoIngrediente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto extends BaseEntity {

    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    private String imagenUrl;
    private String sku;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioBase;

    private Boolean personalizable = false;
    private Boolean controlStock = true;
    private Boolean activo = true;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoIngrediente> ingredientes = new ArrayList<>();
}

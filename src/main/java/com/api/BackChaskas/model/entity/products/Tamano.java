package com.api.BackChaskas.model.entity.products;

import com.api.BackChaskas.model.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tamanos")
@Getter
@Setter
public class Tamano extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(precision = 10, scale = 2)
    private BigDecimal multiplicadorPrecio;

    private Boolean activo = true;
}

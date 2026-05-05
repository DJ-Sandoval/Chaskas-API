package com.api.BackChaskas.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 20)
    private String unit; // gramos, unidad, ml

    @Column(precision = 12, scale = 3)
    private BigDecimal currentStock = BigDecimal.ZERO;

    @Column(precision = 12, scale = 3)
    private BigDecimal minStock = BigDecimal.ZERO;
}

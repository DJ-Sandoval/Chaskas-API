package com.api.BackChaskas.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_sizes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSize extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false, length = 50)
    private String sizeName;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal priceModifier = BigDecimal.ZERO;

    private Integer maxFlavors = 2;
}

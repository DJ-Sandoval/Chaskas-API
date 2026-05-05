package com.api.BackChaskas.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "toppings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topping extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    private Integer stockQuantity = 0;
    private String imageUrl;
    private Boolean active = true;
}

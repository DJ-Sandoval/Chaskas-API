package com.api.BackChaskas.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "flavors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flavor extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    private String description;
    private String color; // Hex
    private String imageUrl;

    private Integer stockGrams = 0;
    private Boolean active = true;
}
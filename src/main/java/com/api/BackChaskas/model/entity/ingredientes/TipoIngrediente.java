package com.api.BackChaskas.model.entity.ingredientes;

import com.api.BackChaskas.model.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// TipoIngrediente.java
@Entity
@Table(name = "tipos_ingredientes")
@Getter
@Setter
public class TipoIngrediente extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String nombre;
}

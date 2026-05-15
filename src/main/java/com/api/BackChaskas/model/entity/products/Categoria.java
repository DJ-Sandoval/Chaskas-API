package com.api.BackChaskas.model.entity.products;

import com.api.BackChaskas.model.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorias")
@Getter
@Setter
public class Categoria extends BaseEntity {

    @Column(length = 100, nullable = false)
    private String nombre;

    private Boolean activo = true;
}

package com.api.BackChaskas.model.entity.ingredientes;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
// UnidadMedida.java
@Entity
@Table(name = "unidades_medida")
@Getter @Setter
public class UnidadMedida extends BaseEntity {

    @Column(length = 20, nullable = false)
    private String clave;

    @Column(length = 50, nullable = false)
    private String nombre;
}

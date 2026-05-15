package com.api.BackChaskas.model.entity.mermas;
import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.ingredientes.Ingrediente;
import com.api.BackChaskas.model.entity.seguridad.Usuario;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;

// Merma.java
@Entity
@Table(name = "mermas")
@Getter @Setter
public class Merma extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(length = 255)
    private String motivo;
}

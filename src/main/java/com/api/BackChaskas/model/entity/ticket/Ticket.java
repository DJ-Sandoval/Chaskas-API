package com.api.BackChaskas.model.entity.ticket;

import com.api.BackChaskas.model.entity.base.BaseEntity;
import com.api.BackChaskas.model.entity.ventas.Venta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Ticket.java
@Entity
@Table(name = "tickets")
@Getter
@Setter
public class Ticket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Column(length = 100, unique = true)
    private String numeroTicket;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private Boolean reimpreso = false;
}

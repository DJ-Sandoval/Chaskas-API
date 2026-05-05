package com.api.BackChaskas.model.entity;

import com.api.BackChaskas.model.enums.DeliveryType;
import com.api.BackChaskas.model.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private OrderType orderType; // POS, ECOMMERCE

    @Column(nullable = false, length = 30)
    private String status; // received, preparing, ready, delivered, cancelled

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    private String paymentMethod;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    private String notes;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<OrderStatusHistory> statusHistory = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Invoice invoice;
}

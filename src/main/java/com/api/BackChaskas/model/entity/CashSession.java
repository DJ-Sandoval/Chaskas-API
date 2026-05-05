package com.api.BackChaskas.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cash_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashSession extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime openedAt;

    private LocalDateTime closedAt;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal initialAmount = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal finalAmount;

    @Column(precision = 12, scale = 2)
    private BigDecimal totalSales = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal totalCash = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal totalCard = BigDecimal.ZERO;

    private String notes;
}

package com.api.BackChaskas.model.entity;

import com.api.BackChaskas.model.embedded.OrderItemToppingId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item_toppings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemTopping {

    @EmbeddedId
    private OrderItemToppingId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderItemId")
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("toppingId")
    @JoinColumn(name = "topping_id", nullable = false)
    private Topping topping;

    private Integer quantity = 1;
}

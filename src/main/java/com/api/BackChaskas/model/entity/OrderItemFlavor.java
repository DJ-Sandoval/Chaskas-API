package com.api.BackChaskas.model.entity;

import com.api.BackChaskas.model.embedded.OrderItemFlavorId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item_flavors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemFlavor {

    @EmbeddedId
    private OrderItemFlavorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderItemId")                    // Mapea con el ID embebido
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("flavorId")   // 🔥 ESTO ES LO QUE TE FALTABA
    @JoinColumn(name = "flavor_id", nullable = false)
    private Flavor flavor;

    private Integer grams;
}

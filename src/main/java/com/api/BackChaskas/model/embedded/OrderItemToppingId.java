package com.api.BackChaskas.model.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderItemToppingId implements Serializable {

    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "topping_id")
    private Long toppingId;
}

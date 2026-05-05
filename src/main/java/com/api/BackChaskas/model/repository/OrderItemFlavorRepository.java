package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.embedded.OrderItemFlavorId;
import com.api.BackChaskas.model.entity.OrderItemFlavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemFlavorRepository extends JpaRepository<OrderItemFlavor, OrderItemFlavorId> {

    void deleteByOrderItemId(Long orderItemId);
}

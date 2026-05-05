package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.embedded.OrderItemToppingId;
import com.api.BackChaskas.model.entity.OrderItemTopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemToppingRepository extends JpaRepository<OrderItemTopping, OrderItemToppingId> {

    void deleteByOrderItemId(Long orderItemId);
}

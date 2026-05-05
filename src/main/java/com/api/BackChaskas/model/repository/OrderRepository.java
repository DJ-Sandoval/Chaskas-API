package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.Order;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {

    List<Order> findByStatus(String status);
    List<Order> findByOrderType(String orderType);
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Order> findByUserId(Long userId);
}

package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.CashSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashSessionRepository extends JpaRepository<CashSession, Long> {

    Optional<CashSession> findFirstByUserIdOrderByOpenedAtDesc(Long userId);
    Optional<CashSession> findByUserIdAndClosedAtIsNull(Long userId); // Caja abierta actual
}

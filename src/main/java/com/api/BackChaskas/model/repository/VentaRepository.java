package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.ventas.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}

package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.ventas.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}

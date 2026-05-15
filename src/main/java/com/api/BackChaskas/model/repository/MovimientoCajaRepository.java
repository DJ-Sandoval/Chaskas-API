package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.cajas.MovimientoCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoCajaRepository extends JpaRepository<MovimientoCaja, Long> {
}
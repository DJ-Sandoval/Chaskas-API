package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.cajas.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaRepository extends JpaRepository<Caja, Long> {
}

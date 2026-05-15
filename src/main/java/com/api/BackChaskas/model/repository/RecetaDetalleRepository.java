package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.recetas.RecetaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaDetalleRepository extends JpaRepository<RecetaDetalle, Long> {
}

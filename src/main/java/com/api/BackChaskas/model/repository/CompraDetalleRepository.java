package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.proveedoresycompras.CompraDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraDetalleRepository extends JpaRepository<CompraDetalle, Long> {
}

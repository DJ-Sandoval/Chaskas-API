package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.ventas.VentaDetalleIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaDetalleIngredienteRepository extends JpaRepository<VentaDetalleIngrediente, Long> {
}

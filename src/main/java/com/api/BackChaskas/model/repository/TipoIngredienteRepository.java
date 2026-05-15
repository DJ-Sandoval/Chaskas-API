package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.ingredientes.TipoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIngredienteRepository extends JpaRepository<TipoIngrediente, Long> {
}

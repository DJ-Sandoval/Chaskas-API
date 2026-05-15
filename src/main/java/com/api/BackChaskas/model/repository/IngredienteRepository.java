package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.ingredientes.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}

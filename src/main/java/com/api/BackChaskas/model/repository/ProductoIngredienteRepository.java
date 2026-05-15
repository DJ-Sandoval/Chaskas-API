package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.ingredientes.ProductoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoIngredienteRepository extends JpaRepository<ProductoIngrediente, Long> {
}

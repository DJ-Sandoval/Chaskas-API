package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.recetas.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
}
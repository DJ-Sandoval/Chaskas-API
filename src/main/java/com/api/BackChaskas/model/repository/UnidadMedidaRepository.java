package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.ingredientes.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Long> {
}

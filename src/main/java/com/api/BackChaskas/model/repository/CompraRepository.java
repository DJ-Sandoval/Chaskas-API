package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.proveedoresycompras.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}

package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.proveedoresycompras.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}

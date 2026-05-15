package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.sucursales.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}

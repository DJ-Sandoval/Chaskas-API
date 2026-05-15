package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.seguridad.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {
}

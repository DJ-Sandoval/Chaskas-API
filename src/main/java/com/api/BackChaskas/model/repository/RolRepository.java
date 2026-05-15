package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.seguridad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}

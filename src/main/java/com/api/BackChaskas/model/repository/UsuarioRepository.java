package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("""
        SELECT DISTINCT u
        FROM Usuario u
        LEFT JOIN FETCH u.rol r
        LEFT JOIN FETCH r.permisos
        WHERE u.email = :email
    """)
    Optional<Usuario> findByEmailWithPermissions(@Param("email") String email);
}

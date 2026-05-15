package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.products.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

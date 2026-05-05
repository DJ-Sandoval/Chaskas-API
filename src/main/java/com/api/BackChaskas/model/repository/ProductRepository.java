package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    List<Product> findByCategory(String category);
    List<Product> findByActiveTrueOrderByNameAsc();
}

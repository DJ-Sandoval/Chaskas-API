package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Long> {

    List<ProductIngredient> findByProductId(Long productId);
    List<ProductIngredient> findByProductIdAndSizeId(Long productId, Long sizeId);
}

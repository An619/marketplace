package com.enterprise.marketplace.productservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.productservice.entity.ProductInventoryEntity;

public interface ProductInventoryRepository extends JpaRepository<ProductInventoryEntity, UUID> {

    Optional<ProductInventoryEntity> findByProductId(UUID productId);
}

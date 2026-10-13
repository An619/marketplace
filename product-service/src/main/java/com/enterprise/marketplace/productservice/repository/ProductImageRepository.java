package com.enterprise.marketplace.productservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.productservice.entity.ProductImageEntity;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, UUID> {

    List<ProductImageEntity> findByProductIdOrderByDisplayOrderAsc(UUID productId);

    void deleteByProductId(UUID productId);
}

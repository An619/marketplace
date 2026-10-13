package com.enterprise.marketplace.productservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.productservice.entity.ProductAttributeEntity;

public interface ProductAttributeRepository extends JpaRepository<ProductAttributeEntity, UUID> {

    List<ProductAttributeEntity> findByProductId(UUID productId);

    void deleteByProductId(UUID productId);
}

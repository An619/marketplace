package com.enterprise.marketplace.productservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.productservice.entity.ProductWorkflowEntity;

public interface ProductWorkflowRepository extends JpaRepository<ProductWorkflowEntity, UUID> {

    Optional<ProductWorkflowEntity> findByProductId(UUID productId);
}

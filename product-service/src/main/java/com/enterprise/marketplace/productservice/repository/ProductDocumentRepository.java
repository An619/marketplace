package com.enterprise.marketplace.productservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.productservice.entity.ProductDocumentEntity;

public interface ProductDocumentRepository extends JpaRepository<ProductDocumentEntity, UUID> {

    List<ProductDocumentEntity> findByProductId(UUID productId);
}

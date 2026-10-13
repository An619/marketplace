package com.enterprise.marketplace.productservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.productservice.entity.ProductPriceEntity;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, UUID> {

    java.util.Optional<ProductPriceEntity> findFirstByProductIdOrderByValidFromDesc(UUID productId);

    void deleteByProductId(UUID productId);
}

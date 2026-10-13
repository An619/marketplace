package com.enterprise.marketplace.productservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.productservice.entity.ProductAuditEntity;

public interface ProductAuditRepository extends JpaRepository<ProductAuditEntity, UUID> {}

package com.enterprise.marketplace.pricingservice.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.enterprise.marketplace.pricingservice.infrastructure.persistence.entity.PricingEntity;

public interface PricingJpaRepository
        extends JpaRepository<PricingEntity, UUID>, JpaSpecificationExecutor<PricingEntity> {}

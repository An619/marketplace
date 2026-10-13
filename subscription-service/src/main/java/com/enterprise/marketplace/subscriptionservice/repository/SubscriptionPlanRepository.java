package com.enterprise.marketplace.subscriptionservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.subscriptionservice.entity.SubscriptionPlanEntity;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlanEntity, UUID> {

    Optional<SubscriptionPlanEntity> findByPlanCodeAndActiveTrue(String planCode);

    Optional<SubscriptionPlanEntity> findByIdAndActiveTrue(UUID id);

    List<SubscriptionPlanEntity> findByActiveTrueOrderByPriceAsc();

    boolean existsByPlanCode(String planCode);
}

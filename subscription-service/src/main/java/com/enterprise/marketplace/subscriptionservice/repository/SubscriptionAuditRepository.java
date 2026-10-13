package com.enterprise.marketplace.subscriptionservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.subscriptionservice.entity.SubscriptionAuditEntity;

public interface SubscriptionAuditRepository extends JpaRepository<SubscriptionAuditEntity, UUID> {}

package com.enterprise.marketplace.aiservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.aiservice.entity.AiAuditEntity;

public interface AiAuditRepository extends JpaRepository<AiAuditEntity, UUID> {}

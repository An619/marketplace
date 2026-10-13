package com.enterprise.marketplace.adminservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.adminservice.entity.AdminAuditEntity;

public interface AdminAuditRepository extends JpaRepository<AdminAuditEntity, UUID> {}

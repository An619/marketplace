package com.enterprise.marketplace.reportservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.reportservice.entity.ReportAuditEntity;

public interface ReportAuditRepository extends JpaRepository<ReportAuditEntity, UUID> {}

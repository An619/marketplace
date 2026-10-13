package com.enterprise.marketplace.auditservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.auditservice.entity.AuditEventLogEntity;

public interface AuditEventLogRepository extends JpaRepository<AuditEventLogEntity, UUID> {

    List<AuditEventLogEntity> findByAuditRecordId(UUID auditRecordId);

    List<AuditEventLogEntity> findByRequestId(String requestId);

    List<AuditEventLogEntity> findByCorrelationId(String correlationId);

    List<AuditEventLogEntity> findByProcessedFalseOrderByCreatedAtAsc();
}

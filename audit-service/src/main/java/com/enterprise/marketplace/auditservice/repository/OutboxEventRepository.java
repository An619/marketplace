package com.enterprise.marketplace.auditservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.auditservice.entity.OutboxEventEntity;
import com.enterprise.marketplace.auditservice.enums.OutboxEventStatus;

public interface OutboxEventRepository extends JpaRepository<OutboxEventEntity, UUID> {

    List<OutboxEventEntity> findTop50ByStatusOrderByCreatedAtAsc(OutboxEventStatus status);
}

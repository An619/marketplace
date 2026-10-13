package com.enterprise.marketplace.adminservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.adminservice.entity.OutboxEventEntity;
import com.enterprise.marketplace.adminservice.enums.OutboxEventStatus;

public interface OutboxEventRepository extends JpaRepository<OutboxEventEntity, UUID> {

    List<OutboxEventEntity> findTop50ByStatusOrderByCreatedAtAsc(OutboxEventStatus status);
}

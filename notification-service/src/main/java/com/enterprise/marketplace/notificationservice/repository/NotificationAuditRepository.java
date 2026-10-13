package com.enterprise.marketplace.notificationservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.notificationservice.entity.NotificationAuditEntity;

public interface NotificationAuditRepository extends JpaRepository<NotificationAuditEntity, UUID> {

    List<NotificationAuditEntity> findByNotificationIdOrderByCreatedAtDesc(UUID notificationId);
}

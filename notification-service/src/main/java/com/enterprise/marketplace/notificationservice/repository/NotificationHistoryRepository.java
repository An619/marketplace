package com.enterprise.marketplace.notificationservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.notificationservice.entity.NotificationHistoryEntity;

public interface NotificationHistoryRepository
        extends JpaRepository<NotificationHistoryEntity, UUID> {

    List<NotificationHistoryEntity> findByNotificationIdOrderByCreatedAtAsc(UUID notificationId);
}

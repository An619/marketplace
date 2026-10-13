package com.enterprise.marketplace.notificationservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.notificationservice.entity.NotificationDeliveryEntity;

public interface NotificationDeliveryRepository
        extends JpaRepository<NotificationDeliveryEntity, UUID> {

    List<NotificationDeliveryEntity> findByNotificationIdOrderByCreatedAtDesc(UUID notificationId);
}

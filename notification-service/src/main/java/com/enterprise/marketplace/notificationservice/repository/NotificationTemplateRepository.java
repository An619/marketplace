package com.enterprise.marketplace.notificationservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.notificationservice.entity.NotificationTemplateEntity;
import com.enterprise.marketplace.notificationservice.enums.NotificationChannel;

public interface NotificationTemplateRepository
        extends JpaRepository<NotificationTemplateEntity, UUID> {

    Optional<NotificationTemplateEntity> findByTemplateCodeAndChannelAndActiveTrue(
            String templateCode, NotificationChannel channel);
}

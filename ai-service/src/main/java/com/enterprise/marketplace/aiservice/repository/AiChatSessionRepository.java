package com.enterprise.marketplace.aiservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.aiservice.entity.AiChatSessionEntity;

public interface AiChatSessionRepository extends JpaRepository<AiChatSessionEntity, UUID> {

    Optional<AiChatSessionEntity> findBySessionKey(String sessionKey);
}

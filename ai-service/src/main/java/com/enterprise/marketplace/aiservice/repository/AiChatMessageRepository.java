package com.enterprise.marketplace.aiservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.aiservice.entity.AiChatMessageEntity;

public interface AiChatMessageRepository extends JpaRepository<AiChatMessageEntity, UUID> {

    List<AiChatMessageEntity> findTop20BySessionIdOrderByCreatedAtAsc(UUID sessionId);
}

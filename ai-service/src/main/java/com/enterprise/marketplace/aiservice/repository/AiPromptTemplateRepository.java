package com.enterprise.marketplace.aiservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.aiservice.entity.AiPromptTemplateEntity;
import com.enterprise.marketplace.aiservice.enums.AiUseCase;

public interface AiPromptTemplateRepository extends JpaRepository<AiPromptTemplateEntity, UUID> {

    Optional<AiPromptTemplateEntity> findByTemplateCodeAndActiveTrue(String templateCode);

    Optional<AiPromptTemplateEntity> findFirstByUseCaseAndActiveTrue(AiUseCase useCase);
}

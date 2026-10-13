package com.enterprise.marketplace.buyerservice.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.enterprise.marketplace.buyerservice.infrastructure.persistence.entity.BuyerEntity;

public interface BuyerJpaRepository
        extends JpaRepository<BuyerEntity, UUID>, JpaSpecificationExecutor<BuyerEntity> {

    Optional<BuyerEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, UUID id);
}

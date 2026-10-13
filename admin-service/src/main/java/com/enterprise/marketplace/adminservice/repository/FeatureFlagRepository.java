package com.enterprise.marketplace.adminservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.adminservice.entity.FeatureFlagEntity;

public interface FeatureFlagRepository extends JpaRepository<FeatureFlagEntity, UUID> {

    Optional<FeatureFlagEntity> findByFlagKey(String flagKey);

    List<FeatureFlagEntity> findAllByOrderByFlagKeyAsc();

    long countByEnabledTrue();
}

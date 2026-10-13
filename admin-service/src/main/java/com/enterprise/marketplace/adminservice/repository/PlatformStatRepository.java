package com.enterprise.marketplace.adminservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.adminservice.entity.PlatformStatEntity;

public interface PlatformStatRepository extends JpaRepository<PlatformStatEntity, UUID> {

    Optional<PlatformStatEntity> findByMetricKey(String metricKey);

    List<PlatformStatEntity> findAllByOrderByCategoryAscMetricKeyAsc();
}

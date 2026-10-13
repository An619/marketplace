package com.enterprise.marketplace.adminservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.adminservice.entity.PlatformSettingEntity;

public interface PlatformSettingRepository extends JpaRepository<PlatformSettingEntity, UUID> {

    Optional<PlatformSettingEntity> findBySettingKey(String settingKey);

    List<PlatformSettingEntity> findByCategoryOrderBySettingKeyAsc(String category);

    List<PlatformSettingEntity> findAllByOrderBySettingKeyAsc();

    long countByActiveTrue();
}

package com.enterprise.marketplace.reportservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.reportservice.entity.ReportResultEntity;

public interface ReportResultRepository extends JpaRepository<ReportResultEntity, UUID> {

    Optional<ReportResultEntity> findByJobId(UUID jobId);
}

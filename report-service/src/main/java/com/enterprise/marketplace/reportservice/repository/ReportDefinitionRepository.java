package com.enterprise.marketplace.reportservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.reportservice.entity.ReportDefinitionEntity;

public interface ReportDefinitionRepository extends JpaRepository<ReportDefinitionEntity, UUID> {

    Optional<ReportDefinitionEntity> findByReportCodeAndActiveTrue(String reportCode);

    List<ReportDefinitionEntity> findByActiveTrueOrderByReportCodeAsc();
}

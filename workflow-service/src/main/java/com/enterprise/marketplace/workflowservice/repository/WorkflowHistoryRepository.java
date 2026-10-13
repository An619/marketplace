package com.enterprise.marketplace.workflowservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.workflowservice.entity.WorkflowHistoryEntity;

public interface WorkflowHistoryRepository extends JpaRepository<WorkflowHistoryEntity, UUID> {

    List<WorkflowHistoryEntity> findByWorkflowIdOrderByCreatedAtAsc(UUID workflowId);
}

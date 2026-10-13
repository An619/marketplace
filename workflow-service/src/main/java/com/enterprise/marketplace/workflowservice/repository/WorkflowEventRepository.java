package com.enterprise.marketplace.workflowservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.workflowservice.entity.WorkflowEventEntity;

public interface WorkflowEventRepository extends JpaRepository<WorkflowEventEntity, UUID> {

    List<WorkflowEventEntity> findByWorkflowIdOrderByCreatedAtDesc(UUID workflowId);
}

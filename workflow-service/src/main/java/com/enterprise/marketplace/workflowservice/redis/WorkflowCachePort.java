package com.enterprise.marketplace.workflowservice.redis;

import java.util.UUID;

import com.enterprise.marketplace.workflowservice.dto.WorkflowResponse;
import com.enterprise.marketplace.workflowservice.enums.WorkflowStatus;

public interface WorkflowCachePort {

    void cacheWorkflow(WorkflowResponse response);

    void evictWorkflow(UUID workflowId);

    void refreshTransitionRules();

    boolean isTransitionAllowed(WorkflowStatus fromStatus, WorkflowStatus toStatus);
}

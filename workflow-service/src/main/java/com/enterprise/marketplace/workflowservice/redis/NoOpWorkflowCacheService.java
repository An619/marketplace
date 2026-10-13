package com.enterprise.marketplace.workflowservice.redis;

import java.util.UUID;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.enterprise.marketplace.workflowservice.dto.WorkflowResponse;
import com.enterprise.marketplace.workflowservice.enums.WorkflowStatus;

@Service
@ConditionalOnProperty(prefix = "marketplace.redis", name = "enabled", havingValue = "false")
public class NoOpWorkflowCacheService implements WorkflowCachePort {

    @Override
    public void cacheWorkflow(WorkflowResponse response) {
        // no-op when redis disabled
    }

    @Override
    public void evictWorkflow(UUID workflowId) {
        // no-op when redis disabled
    }

    @Override
    public void refreshTransitionRules() {
        // no-op when redis disabled
    }

    @Override
    public boolean isTransitionAllowed(WorkflowStatus fromStatus, WorkflowStatus toStatus) {
        return false;
    }
}

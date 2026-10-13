package com.enterprise.marketplace.workflowservice.service;

import java.util.UUID;

import com.enterprise.marketplace.workflowservice.dto.CreateWorkflowRequest;
import com.enterprise.marketplace.workflowservice.dto.StatusUpdateRequest;
import com.enterprise.marketplace.workflowservice.dto.UpdateWorkflowRequest;
import com.enterprise.marketplace.workflowservice.dto.WorkflowPageResponse;
import com.enterprise.marketplace.workflowservice.dto.WorkflowResponse;
import com.enterprise.marketplace.workflowservice.enums.WorkflowStatus;

public interface WorkflowService {

    WorkflowResponse createWorkflow(CreateWorkflowRequest request);

    WorkflowResponse getWorkflow(UUID workflowId);

    WorkflowResponse getWorkflowByRequestId(String requestId);

    WorkflowPageResponse getWorkflowsByStatus(WorkflowStatus status, int page, int size);

    WorkflowResponse updateWorkflow(UUID workflowId, UpdateWorkflowRequest request);

    WorkflowResponse updateStatus(UUID workflowId, StatusUpdateRequest request);

    void deleteWorkflow(UUID workflowId);

    WorkflowResponse advanceWorkflowFromEvent(
            String requestId, WorkflowStatus targetStatus, String eventSource, String payload);
}

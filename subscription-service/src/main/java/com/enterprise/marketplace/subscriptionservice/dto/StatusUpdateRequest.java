package com.enterprise.marketplace.subscriptionservice.dto;

import jakarta.validation.constraints.NotNull;

import com.enterprise.marketplace.subscriptionservice.enums.SubscriptionStatus;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StatusUpdateRequest {

    @NotNull SubscriptionStatus status;
}

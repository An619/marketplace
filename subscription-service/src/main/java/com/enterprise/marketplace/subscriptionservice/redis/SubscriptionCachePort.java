package com.enterprise.marketplace.subscriptionservice.redis;

import java.util.Optional;
import java.util.UUID;

import com.enterprise.marketplace.subscriptionservice.dto.PlanResponse;
import com.enterprise.marketplace.subscriptionservice.dto.SubscriptionResponse;

public interface SubscriptionCachePort {

    void cacheSubscription(SubscriptionResponse response);

    void evictSubscription(UUID subscriptionId);

    Optional<SubscriptionResponse> getSubscription(UUID subscriptionId);

    void cachePlan(PlanResponse response);

    void evictPlan(UUID planId, String planCode);

    Optional<PlanResponse> getPlan(UUID planId);

    Optional<PlanResponse> getPlanByCode(String planCode);
}

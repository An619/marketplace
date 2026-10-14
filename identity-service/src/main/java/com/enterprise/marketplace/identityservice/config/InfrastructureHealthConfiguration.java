package com.enterprise.marketplace.identityservice.config;

import java.util.Map;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.marketplace.common.api.ApiResponse;
import com.enterprise.marketplace.identityservice.infrastructure.InfrastructureHealthAggregator;

@RestController
@RequestMapping("/api/v1/infrastructure")
public class InfrastructureHealthConfiguration {

    private final ObjectProvider<InfrastructureHealthAggregator> healthAggregatorProvider;

    public InfrastructureHealthConfiguration(
            ObjectProvider<InfrastructureHealthAggregator> healthAggregatorProvider) {
        this.healthAggregatorProvider = healthAggregatorProvider;
    }

    @GetMapping("/health")
    @ResponseBody
    ResponseEntity<ApiResponse<Map<String, Object>>> infrastructureHealth() {
        InfrastructureHealthAggregator aggregator = healthAggregatorProvider.getIfAvailable();
        if (aggregator == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        Map<String, Object> payload = aggregator.collectHealth();
        return ResponseEntity.ok(ApiResponse.success(payload, "Infrastructure health snapshot"));
    }

    @GetMapping("/health/redis")
    @ResponseBody
    ResponseEntity<ApiResponse<Map<String, Object>>> redisHealth() {
        InfrastructureHealthAggregator aggregator = healthAggregatorProvider.getIfAvailable();
        if (aggregator == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        Map<String, Object> payload = aggregator.collectHealth();
        Object components = payload.get("components");
        if (!(components instanceof Map<?, ?> componentHealth)
                || componentHealth.get("redis") == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        return ResponseEntity.ok(
                ApiResponse.success(
                        Map.of("redis", componentHealth.get("redis")), "Redis health"));
    }

    @GetMapping("/health/kafka")
    @ResponseBody
    ResponseEntity<ApiResponse<Map<String, Object>>> kafkaHealth() {
        InfrastructureHealthAggregator aggregator = healthAggregatorProvider.getIfAvailable();
        if (aggregator == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        Map<String, Object> payload = aggregator.collectHealth();
        Object components = payload.get("components");
        if (!(components instanceof Map<?, ?> componentHealth)
                || componentHealth.get("kafka") == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        return ResponseEntity.ok(
                ApiResponse.success(
                        Map.of("kafka", componentHealth.get("kafka")), "Kafka health"));
    }
}

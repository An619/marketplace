package com.enterprise.marketplace.identityservice.infrastructure;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

/** Aggregated infrastructure health for Redis and Kafka connectivity. */
@Component
public class InfrastructureHealthAggregator {

    private final ObjectProvider<RedisConnectionFactory> redisConnectionFactoryProvider;
    private final ObjectProvider<KafkaAdmin> kafkaAdminProvider;

    public InfrastructureHealthAggregator(
            ObjectProvider<RedisConnectionFactory> redisConnectionFactoryProvider,
            ObjectProvider<KafkaAdmin> kafkaAdminProvider) {
        this.redisConnectionFactoryProvider = redisConnectionFactoryProvider;
        this.kafkaAdminProvider = kafkaAdminProvider;
    }

    public Map<String, Object> collectHealth() {
        Map<String, Object> components = new LinkedHashMap<>();
        components.put("redis", checkRedis());
        components.put("kafka", checkKafka());

        Map<String, Object> health = new LinkedHashMap<>();
        health.put("components", components);
        health.put("status", overallStatus(components));
        return health;
    }

    private Map<String, Object> checkRedis() {
        Map<String, Object> redisHealth = new LinkedHashMap<>();
        RedisConnectionFactory redisConnectionFactory =
                redisConnectionFactoryProvider.getIfAvailable();
        if (redisConnectionFactory == null) {
            redisHealth.put("status", "UNAVAILABLE");
            redisHealth.put("error", "Redis connection factory is not configured");
            return redisHealth;
        }

        try {
            String pong = redisConnectionFactory.getConnection().ping();
            redisHealth.put("status", "UP");
            redisHealth.put("response", pong);
        } catch (Exception ex) {
            redisHealth.put("status", "DOWN");
            redisHealth.put("error", ex.getMessage());
        }
        return redisHealth;
    }

    private Map<String, Object> checkKafka() {
        Map<String, Object> kafkaHealth = new LinkedHashMap<>();
        KafkaAdmin kafkaAdmin = kafkaAdminProvider.getIfAvailable();
        if (kafkaAdmin == null) {
            kafkaHealth.put("status", "UNAVAILABLE");
            kafkaHealth.put("error", "Kafka admin is not configured");
            return kafkaHealth;
        }

        try {
            kafkaAdmin.initialize();
            kafkaHealth.put("status", "UP");
            kafkaHealth.put(
                    "bootstrapServers",
                    kafkaAdmin.getConfigurationProperties().get("bootstrap.servers"));
        } catch (Exception ex) {
            kafkaHealth.put("status", "DOWN");
            kafkaHealth.put("error", ex.getMessage());
        }
        return kafkaHealth;
    }

    private String overallStatus(Map<String, Object> components) {
        boolean allUp =
                components.values().stream()
                        .filter(Map.class::isInstance)
                        .map(value -> (Map<?, ?>) value)
                        .allMatch(
                                component -> "UP".equals(String.valueOf(component.get("status"))));
        return allUp ? "UP" : "DOWN";
    }
}

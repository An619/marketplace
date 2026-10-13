package com.enterprise.marketplace.subscriptionservice.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriptionOpenApiConfig {

    @Bean
    public GroupedOpenApi subscriptionGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("subscription-service")
                .pathsToMatch(
                        "/api/v1/subscriptions/**", "/api/v1/plans/**", "/api/v1/bootstrap/**")
                .displayName("Subscription Service API")
                .build();
    }
}

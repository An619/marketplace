package com.enterprise.marketplace.subscriptionservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "marketplace.subscription")
public class SubscriptionProperties {

    private int defaultPageSize = 20;
    private int maxPageSize = 100;
}

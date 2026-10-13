package com.enterprise.marketplace.subscriptionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.enterprise.marketplace.common.util.LoggingUtility;

@SpringBootApplication
public class SubscriptionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionServiceApplication.class, args);
        LoggingUtility.setServiceName("subscription-service");
    }
}

package com.enterprise.marketplace.adminservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "marketplace.admin")
public class AdminProperties {

    private int defaultPageSize = 20;
    private int maxPageSize = 100;
}

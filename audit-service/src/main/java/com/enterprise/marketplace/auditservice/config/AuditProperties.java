package com.enterprise.marketplace.auditservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "marketplace.audit")
public class AuditProperties {

    private int defaultPageSize = 20;
    private int maxPageSize = 100;
}

package com.enterprise.marketplace.reportservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "marketplace.report")
public class ReportProperties {

    private int defaultPageSize = 20;
    private int maxPageSize = 100;
    private boolean generationEnabled = true;
    private long processIntervalMs = 5000;
    private boolean autoTriggerOnEvents = true;
}

package com.enterprise.marketplace.searchservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "marketplace.search")
public class SearchProperties {

    private int defaultPageSize = 20;
    private int maxPageSize = 100;
}

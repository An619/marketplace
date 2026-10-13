package com.enterprise.marketplace.adminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.enterprise.marketplace.common.util.LoggingUtility;

@SpringBootApplication
public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
        LoggingUtility.setServiceName("admin-service");
    }
}

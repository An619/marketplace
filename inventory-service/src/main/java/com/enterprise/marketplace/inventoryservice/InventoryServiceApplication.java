package com.enterprise.marketplace.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.enterprise.marketplace.common.util.LoggingUtility;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
        LoggingUtility.setServiceName("inventory-service");
    }
}

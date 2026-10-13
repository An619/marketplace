package com.enterprise.marketplace.identityservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        properties = {
            "spring.profiles.active=standalone",
            "marketplace.security.enabled=false",
            "marketplace.kafka.enabled=false"
        })
class IdentityServiceApplicationTest {

    @Test
    void contextLoads() {}
}

package com.enterprise.marketplace.aiservice.bootstrap;

import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.marketplace.aiservice.infrastructure.OllamaHealthIndicator;
import com.enterprise.marketplace.common.api.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/infrastructure")
@RequiredArgsConstructor
public class AiInfrastructureHealthController {

    private final OllamaHealthIndicator ollamaHealthIndicator;

    @GetMapping("/health/ollama")
    public ResponseEntity<ApiResponse<Map<String, Object>>> ollamaHealth() {
        Health health = ollamaHealthIndicator.health();
        Map<String, Object> payload =
                Map.of(
                        "status", health.getStatus().getCode(),
                        "details", health.getDetails());
        return ResponseEntity.ok(ApiResponse.success(payload, "Ollama health"));
    }
}

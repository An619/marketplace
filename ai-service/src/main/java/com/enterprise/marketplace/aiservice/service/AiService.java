package com.enterprise.marketplace.aiservice.service;

import java.util.UUID;

import com.enterprise.marketplace.aiservice.dto.ChatRequest;
import com.enterprise.marketplace.aiservice.dto.ChatResponse;
import com.enterprise.marketplace.aiservice.dto.GenerateDescriptionRequest;
import com.enterprise.marketplace.aiservice.dto.GenerateDescriptionResponse;
import com.enterprise.marketplace.aiservice.dto.RecommendationResponse;
import com.enterprise.marketplace.aiservice.dto.SearchInterpretRequest;
import com.enterprise.marketplace.aiservice.dto.SearchInterpretResponse;

public interface AiService {

    ChatResponse chat(ChatRequest request);

    GenerateDescriptionResponse generateDescription(GenerateDescriptionRequest request);

    SearchInterpretResponse interpretSearch(SearchInterpretRequest request);

    RecommendationResponse getRecommendations(String buyerId, UUID categoryId, int limit);

    void processKafkaEvent(String payload, String eventSource);
}

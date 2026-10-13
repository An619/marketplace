package com.enterprise.marketplace.identityservice.application.dto;

import java.util.UUID;

import com.enterprise.marketplace.identityservice.domain.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QrStatusResponse {
    String qrSessionId;
    String status;
    String accessToken;
    UUID userId;
    UserType userType;
}

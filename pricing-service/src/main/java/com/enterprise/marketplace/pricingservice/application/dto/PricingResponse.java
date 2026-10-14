package com.enterprise.marketplace.pricingservice.application.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.enterprise.marketplace.pricingservice.domain.model.PricingStatus;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PricingResponse {

    UUID id;
    UUID productId;
    UUID sellerId;
    BigDecimal unitPrice;
    String currency;
    Integer minQuantity;
    BigDecimal discountPercent;
    Instant validFrom;
    Instant validTo;
    PricingStatus status;
    Instant createdAt;
    Instant updatedAt;
    Long version;
}

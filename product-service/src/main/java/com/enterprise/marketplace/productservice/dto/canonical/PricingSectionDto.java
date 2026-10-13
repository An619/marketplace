package com.enterprise.marketplace.productservice.dto.canonical;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class PricingSectionDto {

    @NotNull BigDecimal unitPrice;

    @NotBlank String currency;

    Integer minQuantity;

    BigDecimal discountPercent;

    Instant validFrom;

    Instant validTo;
}

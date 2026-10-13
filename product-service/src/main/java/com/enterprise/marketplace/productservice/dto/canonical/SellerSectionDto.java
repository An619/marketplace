package com.enterprise.marketplace.productservice.dto.canonical;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerSectionDto {

    @NotNull UUID sellerId;
}

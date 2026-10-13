package com.enterprise.marketplace.productservice.dto.canonical;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestHeaderDto {

    @NotBlank String sourceSystem;

    @NotBlank String channel;

    @NotBlank String locale;

    String tenantId;
}

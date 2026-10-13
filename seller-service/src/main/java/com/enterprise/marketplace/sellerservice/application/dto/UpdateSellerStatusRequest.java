package com.enterprise.marketplace.sellerservice.application.dto;

import jakarta.validation.constraints.NotNull;

import com.enterprise.marketplace.sellerservice.domain.model.SellerStatus;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UpdateSellerStatusRequest {

    @NotNull SellerStatus status;
}

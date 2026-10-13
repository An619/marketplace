package com.enterprise.marketplace.identityservice.application.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class VerifyPinRequest {
    private String countryCode = "+91";
    private String mobileNumber;
    private UUID userId;

    @NotBlank @Pattern(regexp = "^\\d{6}$", message = "pin must be 6 digits") private String pin;
}

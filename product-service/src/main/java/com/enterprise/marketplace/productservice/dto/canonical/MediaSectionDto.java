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
public class MediaSectionDto {

    @NotBlank String url;

    String altText;

    Integer displayOrder;

    Boolean primaryImage;
}

package com.enterprise.marketplace.productservice.dto.canonical;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPageResponse {

    List<ProductDetailResponse> content;
    long totalElements;
    int totalPages;
    int page;
    int size;
}

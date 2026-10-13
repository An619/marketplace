package com.enterprise.marketplace.searchservice.service;

import java.util.UUID;

import com.enterprise.marketplace.searchservice.dto.ProductSearchPageResponse;
import com.enterprise.marketplace.searchservice.dto.ProductSearchRequest;
import com.enterprise.marketplace.searchservice.dto.ProductSearchResult;

public interface SearchService {

    ProductSearchPageResponse searchProducts(ProductSearchRequest request);

    ProductSearchResult getProductById(UUID productId);

    void processIndexEvent(String payload, String eventSource);

    ProductSearchResult reindexProduct(UUID productId, String payload);
}

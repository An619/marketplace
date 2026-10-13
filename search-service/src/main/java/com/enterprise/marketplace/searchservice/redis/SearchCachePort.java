package com.enterprise.marketplace.searchservice.redis;

import java.util.Optional;

import com.enterprise.marketplace.searchservice.dto.ProductSearchPageResponse;

public interface SearchCachePort {

    void cacheSearchResult(String cacheKey, ProductSearchPageResponse response);

    Optional<ProductSearchPageResponse> getSearchResult(String cacheKey);
}

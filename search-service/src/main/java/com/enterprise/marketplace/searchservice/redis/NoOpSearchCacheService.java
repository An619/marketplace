package com.enterprise.marketplace.searchservice.redis;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.enterprise.marketplace.searchservice.dto.ProductSearchPageResponse;

@Service
@ConditionalOnProperty(prefix = "marketplace.redis", name = "enabled", havingValue = "false")
public class NoOpSearchCacheService implements SearchCachePort {

    @Override
    public void cacheSearchResult(String cacheKey, ProductSearchPageResponse response) {
        // no-op
    }

    @Override
    public Optional<ProductSearchPageResponse> getSearchResult(String cacheKey) {
        return Optional.empty();
    }
}

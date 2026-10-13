package com.enterprise.marketplace.searchservice.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.enterprise.marketplace.searchservice.document.ProductSearchDocument;

public interface ProductSearchRepository
        extends ElasticsearchRepository<ProductSearchDocument, String> {}

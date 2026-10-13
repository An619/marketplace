package com.enterprise.marketplace.inventoryservice.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.enterprise.marketplace.inventoryservice.domain.model.InventorySearchCriteria;
import com.enterprise.marketplace.inventoryservice.infrastructure.persistence.entity.InventoryEntity;

final class InventorySpecifications {

    private InventorySpecifications() {}

    static Specification<InventoryEntity> fromCriteria(InventorySearchCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getProductId() != null) {
                predicates.add(cb.equal(root.get("productId"), criteria.getProductId()));
            }
            if (criteria.getSellerId() != null) {
                predicates.add(cb.equal(root.get("sellerId"), criteria.getSellerId()));
            }
            if (criteria.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), criteria.getStatus()));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}

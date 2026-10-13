package com.enterprise.marketplace.searchservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.searchservice.entity.SearchAuditEntity;

public interface SearchAuditRepository extends JpaRepository<SearchAuditEntity, UUID> {}

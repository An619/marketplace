package com.enterprise.marketplace.searchservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.searchservice.entity.SearchSyncLogEntity;

public interface SearchSyncLogRepository extends JpaRepository<SearchSyncLogEntity, UUID> {}

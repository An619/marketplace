package com.enterprise.marketplace.inventoryservice.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.enterprise.marketplace.inventoryservice.infrastructure.persistence.entity.InventoryEntity;

public interface InventoryJpaRepository
        extends JpaRepository<InventoryEntity, UUID>, JpaSpecificationExecutor<InventoryEntity> {

    boolean existsByProductIdAndSellerIdAndWarehouseCode(
            UUID productId, UUID sellerId, String warehouseCode);
}

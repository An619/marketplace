package com.enterprise.marketplace.identityservice.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.marketplace.identityservice.infrastructure.persistence.entity.MobileUserEntity;

public interface MobileUserJpaRepository extends JpaRepository<MobileUserEntity, UUID> {

    Optional<MobileUserEntity> findByCountryCodeAndMobileNumber(
            String countryCode, String mobileNumber);

    boolean existsByCountryCodeAndMobileNumber(String countryCode, String mobileNumber);
}

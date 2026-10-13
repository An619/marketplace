package com.enterprise.marketplace.buyerservice.domain.port;

import java.util.Optional;
import java.util.UUID;

import com.enterprise.marketplace.buyerservice.domain.model.Buyer;
import com.enterprise.marketplace.buyerservice.domain.model.BuyerPage;
import com.enterprise.marketplace.buyerservice.domain.model.BuyerSearchCriteria;

/** Outbound port for buyer persistence. */
public interface BuyerRepository {

    Buyer save(Buyer buyer);

    Optional<Buyer> findById(UUID id);

    Optional<Buyer> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, UUID id);

    BuyerPage<Buyer> search(BuyerSearchCriteria criteria, int page, int size, String sort);

    void deleteById(UUID id);
}

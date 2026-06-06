package com.sponsrnet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.sponsrnet.entity.Offer;
import com.sponsrnet.entity.Opportunity;
import com.sponsrnet.entity.User;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByOpportunity(Opportunity opportunity);

    List<Offer> findBySponsor(User sponsor);

    Optional<Offer> findByOpportunityAndSponsor(
            Opportunity opportunity,
            User sponsor);

    @Modifying
    @Transactional
    void deleteByOpportunity(
            Opportunity opportunity);
}
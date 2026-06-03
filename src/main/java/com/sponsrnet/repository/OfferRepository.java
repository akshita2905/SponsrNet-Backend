package com.sponsrnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sponsrnet.entity.Offer;
import com.sponsrnet.entity.Opportunity;
import com.sponsrnet.entity.User;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByOpportunity(Opportunity opportunity);

    List<Offer> findBySponsor(User sponsor);

}
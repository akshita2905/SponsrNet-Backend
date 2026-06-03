package com.sponsrnet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sponsrnet.entity.Offer;
import com.sponsrnet.entity.Opportunity;
import com.sponsrnet.entity.User;
import com.sponsrnet.repository.OfferRepository;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Optional<Offer> getOfferById(Long id) {
        return offerRepository.findById(id);
    }

    public List<Offer> getOffersByOpportunity(Opportunity opportunity) {
        return offerRepository.findByOpportunity(opportunity);
    }

    public List<Offer> getOffersBySponsor(User sponsor) {
        return offerRepository.findBySponsor(sponsor);
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }
}
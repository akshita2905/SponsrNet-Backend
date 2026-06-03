package com.sponsrnet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sponsrnet.entity.Offer;
import com.sponsrnet.entity.Opportunity;
import com.sponsrnet.entity.User;
import com.sponsrnet.repository.OfferRepository;
import com.sponsrnet.repository.OpportunityRepository;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;

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

    public Offer acceptOffer(Long offerId) {

    Offer offer = offerRepository.findById(offerId)
            .orElseThrow(() -> new RuntimeException("Offer not found"));

    if ("ACCEPTED".equals(offer.getStatus())) {
        throw new RuntimeException("Offer already accepted");
    }

    if ("REJECTED".equals(offer.getStatus())) {
        throw new RuntimeException("Rejected offer cannot be accepted");
    }

    offer.setStatus("ACCEPTED");

    Opportunity opportunity = offer.getOpportunity();

    opportunity.setCurrentAmount(
            opportunity.getCurrentAmount() + offer.getAmount()
    );

    opportunityRepository.save(opportunity);

    return offerRepository.save(offer);
}

    public Offer rejectOffer(Long offerId) {

    Offer offer = offerRepository.findById(offerId)
            .orElseThrow(() -> new RuntimeException("Offer not found"));

    if ("ACCEPTED".equals(offer.getStatus())) {
        throw new RuntimeException("Accepted offer cannot be rejected");
    }

    if ("REJECTED".equals(offer.getStatus())) {
        throw new RuntimeException("Offer already rejected");
    }

    offer.setStatus("REJECTED");

    return offerRepository.save(offer);
}
}
package com.sponsrnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sponsrnet.dto.DashboardDTO;
import com.sponsrnet.entity.Opportunity;
import com.sponsrnet.repository.OfferRepository;
import com.sponsrnet.repository.OpportunityRepository;
import com.sponsrnet.repository.UserRepository;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private OfferRepository offerRepository;

    public DashboardDTO getDashboardStats() {

        long totalUsers = userRepository.count();

        long totalOpportunities = opportunityRepository.count();

        long totalOffers = offerRepository.count();

        long acceptedOffers = offerRepository.findAll()
                .stream()
                .filter(offer -> "ACCEPTED".equals(offer.getStatus()))
                .count();

        double totalFundingRaised = opportunityRepository.findAll()
                .stream()
                .mapToDouble(Opportunity::getCurrentAmount)
                .sum();

        return new DashboardDTO(
                totalUsers,
                totalOpportunities,
                totalOffers,
                acceptedOffers,
                totalFundingRaised
        );
    }
}
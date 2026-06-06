package com.sponsrnet.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sponsrnet.entity.Opportunity;
import com.sponsrnet.entity.User;
import com.sponsrnet.repository.OfferRepository;
import com.sponsrnet.repository.OpportunityRepository;

@Service
public class OpportunityService {

    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
private OfferRepository offerRepository;

    public Opportunity saveOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    public List<Opportunity> getAllOpportunities() {

        List<Opportunity> opportunities =
                opportunityRepository.findAll();

        for (Opportunity opportunity : opportunities) {

            if (
                opportunity.getDeadline() != null
                &&
                opportunity.getDeadline().isBefore(
                        LocalDate.now()
                )
                &&
                !"FUNDED".equals(
                        opportunity.getStatus()
                )
            ) {

                opportunity.setStatus(
                        "EXPIRED"
                );

                opportunityRepository.save(
                        opportunity
                );
            }
        }

        return opportunities;
    }

    public Optional<Opportunity> getOpportunityById(Long id) {

        Optional<Opportunity> opportunity =
                opportunityRepository.findById(id);

        opportunity.ifPresent(op -> {

            if (
                op.getDeadline() != null
                &&
                op.getDeadline().isBefore(
                        LocalDate.now()
                )
                &&
                !"FUNDED".equals(
                        op.getStatus()
                )
            ) {

                op.setStatus(
                        "EXPIRED"
                );

                opportunityRepository.save(op);
            }
        });

        return opportunity;
    }

    public List<Opportunity> getOpportunitiesByOrganizer(
            User organizer) {

        return opportunityRepository.findByOrganizer(
                organizer
        );
    }

    public void deleteOpportunity(Long id) {

    Opportunity opportunity =
            opportunityRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Opportunity not found"
                    ));

    offerRepository.deleteByOpportunity(
            opportunity
    );

    opportunityRepository.delete(
            opportunity
    );
}

    public Opportunity updateOpportunity(
            Long id,
            Opportunity updatedOpportunity) {

        Opportunity opportunity =
                opportunityRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Opportunity not found"
                                ));

        opportunity.setTitle(
                updatedOpportunity.getTitle());

        opportunity.setDescription(
                updatedOpportunity.getDescription());

        opportunity.setCategory(
                updatedOpportunity.getCategory());

        opportunity.setTargetAmount(
                updatedOpportunity.getTargetAmount());

        opportunity.setDeadline(
                updatedOpportunity.getDeadline());

        return opportunityRepository.save(
                opportunity
        );
    }
}
package com.sponsrnet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sponsrnet.entity.Opportunity;
import com.sponsrnet.entity.User;
import com.sponsrnet.repository.UserRepository;
import com.sponsrnet.service.OpportunityService;

@RestController
@RequestMapping("/api/opportunities")
@CrossOrigin("*")
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;
    @Autowired
private UserRepository userRepository;

    @PostMapping
public Opportunity createOpportunity(
        @RequestBody Opportunity opportunity) {

    User organizer =
            userRepository.findById(
                    opportunity.getOrganizer().getId()
            )
            .orElseThrow(() ->
                    new RuntimeException(
                            "User not found"
                    ));

    if (
            !"ORGANIZER".equals(
                    organizer.getRole()
            )
    ) {

        throw new RuntimeException(
                "Only organizers can create opportunities"
        );
    }

    return opportunityService.saveOpportunity(
            opportunity
    );
}

    @GetMapping
    public List<Opportunity> getAllOpportunities() {
        return opportunityService.getAllOpportunities();
    }

    @GetMapping("/{id}")
    public Optional<Opportunity> getOpportunityById(@PathVariable Long id) {
        return opportunityService.getOpportunityById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOpportunity(@PathVariable Long id) {
        opportunityService.deleteOpportunity(id);
        return "Opportunity deleted successfully";
    }

    @GetMapping("/organizer/{organizerId}")
public List<Opportunity> getOpportunitiesByOrganizer(
        @PathVariable Long organizerId) {

    User organizer = new User();
    organizer.setId(organizerId);

    return opportunityService.getOpportunitiesByOrganizer(
            organizer);
}

@PutMapping("/{id}")
public Opportunity updateOpportunity(
        @PathVariable Long id,
        @RequestBody Opportunity opportunity) {

    User organizer =
            userRepository.findById(
                    opportunity.getOrganizer().getId()
            )
            .orElseThrow(() ->
                    new RuntimeException(
                            "User not found"
                    ));

    if (
            !"ORGANIZER".equals(
                    organizer.getRole()
            )
    ) {

        throw new RuntimeException(
                "Only organizers can edit opportunities"
        );
    }

    return opportunityService.updateOpportunity(
            id,
            opportunity
    );
}
}
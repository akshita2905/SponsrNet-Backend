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

import com.sponsrnet.entity.Offer;
import com.sponsrnet.entity.Opportunity;
import com.sponsrnet.entity.User;
import com.sponsrnet.repository.UserRepository;
import com.sponsrnet.service.OfferService;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin("*")
public class OfferController {

    @Autowired
    private OfferService offerService;
    @Autowired
private UserRepository userRepository;

    @PostMapping
public Offer createOffer(
        @RequestBody Offer offer) {

    User sponsor =
            userRepository.findById(
                    offer.getSponsor().getId()
            )
            .orElseThrow(() ->
                    new RuntimeException(
                            "User not found"
                    ));

    if (
            !"SPONSOR".equals(
                    sponsor.getRole()
            )
    ) {

        throw new RuntimeException(
                "Only sponsors can submit offers"
        );
    }

    return offerService.saveOffer(
            offer
    );
}

    @GetMapping
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/{id}")
    public Optional<Offer> getOfferById(@PathVariable Long id) {
        return offerService.getOfferById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return "Offer deleted successfully";
    }

    @PutMapping("/{id}/accept")
public Offer acceptOffer(
        @PathVariable Long id,
        @RequestBody User user) {

    User organizer =
            userRepository.findById(
                    user.getId()
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
                "Only organizers can accept offers"
        );
    }

    return offerService.acceptOffer(id);
}

    @PutMapping("/{id}/reject")
public Offer rejectOffer(
        @PathVariable Long id,
        @RequestBody User user) {

    User organizer =
            userRepository.findById(
                    user.getId()
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
                "Only organizers can reject offers"
        );
    }

    return offerService.rejectOffer(id);
}

    @GetMapping("/sponsor/{sponsorId}")
public List<Offer> getOffersBySponsor(
        @PathVariable Long sponsorId) {

    User sponsor = new User();
    sponsor.setId(sponsorId);

    return offerService.getOffersBySponsor(sponsor);
}

@GetMapping("/opportunity/{opportunityId}")
public List<Offer> getOffersByOpportunity(
        @PathVariable Long opportunityId) {

    Opportunity opportunity = new Opportunity();

    opportunity.setId(opportunityId);

    return offerService.getOffersByOpportunity(
            opportunity
    );
}

}
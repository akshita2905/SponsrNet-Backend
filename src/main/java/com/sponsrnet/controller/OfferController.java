package com.sponsrnet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sponsrnet.entity.Offer;
import com.sponsrnet.service.OfferService;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin("*")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping
    public Offer createOffer(@RequestBody Offer offer) {
        return offerService.saveOffer(offer);
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
}
package com.sponsrnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sponsrnet.entity.Opportunity;
import com.sponsrnet.entity.User;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    List<Opportunity> findByOrganizer(User organizer);

}
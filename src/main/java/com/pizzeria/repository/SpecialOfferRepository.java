package com.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.model.SpecialOffer;

@Repository
public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
	
	public List<SpecialOffer> findByTitle(String title);
	
}
	

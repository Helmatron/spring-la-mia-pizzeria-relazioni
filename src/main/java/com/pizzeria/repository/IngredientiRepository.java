package com.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.model.Ingredienti;

@Repository
public interface IngredientiRepository extends JpaRepository<Ingredienti, Long> {
	
	// ordina ingredienti per nome
	List<Ingredienti> findAllByOrderByNameAsc();
	
}

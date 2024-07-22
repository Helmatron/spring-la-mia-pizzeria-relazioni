package com.pizzeria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pizzeria.model.Pizza;
import com.pizzeria.model.SpecialOffer;
import com.pizzeria.repository.PizzaRepository;
import com.pizzeria.repository.SpecialOfferRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private SpecialOfferRepository specialOfferRepository;

	/*
	 * VIEW INDEX PIZZERIA
	 */
	@GetMapping
	public String index(Model model) {
		List<Pizza> pizze = pizzaRepository.findAll();
		model.addAttribute("list", pizze);
		return "index";
	}

	/*
	 * VIEW DETTAGLI PIZZE BY ID
	 */
	@GetMapping("/pizze/dettagli_pizze/{id}")
	public String findPizzaById(@PathVariable("id") Long id, Model model) {
		Pizza pizza = pizzaRepository.getReferenceById(id);
		if (pizza != null) {
			model.addAttribute("pizza", pizza);
			model.addAttribute("findPizzaById", true);
			return "/pizze/dettagli_pizze";
		} else {

			return "redirect:/";
		}
	}

	/*
	 * VIEW RICERCA PER NOME DELLA PIZZA
	 */
	@GetMapping("/search")
	public String findPizzaByName(@RequestParam(name = "name", required = false) String name, Model model) {
		List<Pizza> pizze = new ArrayList<>();

		if (name == null || name.isBlank()) {
			pizze = pizzaRepository.findAll();

		} else {
			pizze = pizzaRepository.findByName(name);
		}
		model.addAttribute("list", pizze);
		return "index";
	}

	/*
	 * CREAZIONE NUOVA PIZZA
	 */
	@GetMapping("/pizze/nuova_pizza")
	public String creaPizza(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "pizze/nuova_pizza";
	}

	@PostMapping("/pizze/nuova_pizza")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "pizze/nuova_pizza";
		}
		pizzaRepository.save(formPizza);
		return "redirect:/";

	}

	/*
	 * EDIT PIZZE DA GESTIONALE.HTML
	 */
	@GetMapping("/pizze/lista_pizze")
	public String gestionale(Model model) {
		List<Pizza> pizze = pizzaRepository.findAll();
		model.addAttribute("list", pizze);
		return "pizze/lista_pizze";
	}

	@GetMapping("/pizze/edit_pizze/{id}")
	public String editPizza(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("pizza", pizzaRepository.findById(id).get());
		
		List<SpecialOffer> specialOffer = specialOfferRepository.findAll();
		model.addAttribute("specialOffer", specialOffer);
		
		return "pizze/edit_pizze";
	}

	@PostMapping("/pizze/edit_pizze/{id}")
	public String updatePizza(@Valid @ModelAttribute("pizza") Pizza pizza,
			@RequestParam(name = "offerId", required = false) String offerId, // Usa String per permettere valori vuoti
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("specialOffer", specialOfferRepository.findAll()); // Ripopola le offerte in caso di
																					// errore
			return "pizze/edit_pizze";
		}

		if (offerId == null || offerId.isEmpty()) {
			pizza.setSpecialOffer(null); // Rimuove l'offerta se offerId è vuoto
		} else {
			Long offerIdLong = Long.parseLong(offerId); // conversione della Stringa in Long
			SpecialOffer offer = specialOfferRepository.findById(offerIdLong)
					.orElseThrow(() -> new IllegalArgumentException("Invalid offer Id:" + offerId));
			pizza.setSpecialOffer(offer); // Imposta l'offerta speciale sulla pizza
		}

		pizzaRepository.save(pizza);
		return "redirect:/pizze/lista_pizze";
	}

	/*
	 * DELETE PIZZE DA GESTIONALE.HTML
	 */

	@PostMapping("/pizze/lista_pizze/{id}")
	public String deletePizza(@PathVariable("id") Long id) {
		pizzaRepository.deleteById(id);
		return "redirect:/pizze/lista_pizze";
	}

}

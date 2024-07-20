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
import com.pizzeria.repository.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaRepository repository;

	/*
	 * VIEW INDEX PIZZERIA
	 */
	@GetMapping
	public String index(Model model) {
		List<Pizza> pizze = repository.findAll();
		model.addAttribute("list", pizze);
		return "index";
	}

	/*
	 * VIEW DETTAGLI PIZZE BY ID
	 */
	@GetMapping("/dettagli_pizze/{id}")
	public String findPizzaById(@PathVariable("id") Integer id, Model model) {
		Pizza pizza = repository.getReferenceById(id);
		if (pizza != null) {
			model.addAttribute("pizza", pizza);
			model.addAttribute("findPizzaById", true);
			return "dettagli_pizze";
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
			pizze = repository.findAll();

		} else {
			pizze = repository.findByName(name);
		}
		model.addAttribute("list", pizze);
		return "index";
	}

	/*
	 * CREAZIONE NUOVA PIZZA
	 */
	@GetMapping("/nuova_pizza")
	public String creaPizza(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "nuova_pizza";
	}

	@PostMapping("/nuova_pizza")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "/nuova_pizza";
		}
		repository.save(formPizza);
		return "redirect:/";

	}

	/*
	 * UPDATE PIZZE DA GESTIONALE.HTML
	 */
	@GetMapping("/gestionale")
	public String gestionale(Model model) {
		List<Pizza> pizze = repository.findAll();
		model.addAttribute("list", pizze);
		return "gestionale";
	}

	@GetMapping("/edit_pizze/{id}")
	public String editPizza(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("pizza", repository.findById(id).get());
		return "edit_pizze";
	}

	@PostMapping("/edit_pizze/{id}")
	public String updatePizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "/edit_pizze";
		}
		repository.save(pizza);
		return "redirect:/gestionale";
	}
	
	/*
	 * DELETE PIZZE DA GESTIONALE.HTML
	 */
	
	@PostMapping("/gestionale/{id}")
	public String deletePizza(@PathVariable("id") Integer id) {
		repository.deleteById(id);
		return "redirect:/gestionale";
	}
	
}

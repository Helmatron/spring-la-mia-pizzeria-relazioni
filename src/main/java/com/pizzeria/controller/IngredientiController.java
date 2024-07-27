package com.pizzeria.controller;

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

import com.pizzeria.model.Ingredienti;
import com.pizzeria.repository.IngredientiRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class IngredientiController {

	@Autowired
	private IngredientiRepository ingredientiRepository;

	// VIEW
	@GetMapping("/ingredients/lista_ingredienti")
	public String listaIngredienti(Model model) {
		List<Ingredienti> ingredientList = ingredientiRepository.findAllByOrderByNameAsc();
		model.addAttribute("ingredientList", ingredientList);
		return "/ingredients/lista_ingredienti";
	}

	// NUOVO INGREDIENTE

	@GetMapping("/ingredients/nuovo_ingrediente")
	public String createNewIngredient(Model model) {
		model.addAttribute("ingredient", new Ingredienti());
		return "ingredients/nuovo_ingrediente";
	}

	@PostMapping("/ingredients/nuovo_ingrediente")
	public String storeNewIngredient(@Valid @ModelAttribute("ingredient") Ingredienti formIngredient,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "ingredients/nuovo_ingrediente";
		}
		ingredientiRepository.save(formIngredient);
		return "redirect:/ingredients/lista_ingredienti";

	}

	// DELETE INGREDIENTE

	@PostMapping("/ingredients/lista_ingredienti/{id}")
	public String deleteIngredient(@PathVariable("id") Long id) {
		ingredientiRepository.deleteById(id);
		return "redirect:/ingredients/lista_ingredienti";
	}
	
}

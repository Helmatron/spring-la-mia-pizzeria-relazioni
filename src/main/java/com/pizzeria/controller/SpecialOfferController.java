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

import com.pizzeria.model.SpecialOffer;
import com.pizzeria.repository.SpecialOfferRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class SpecialOfferController {

	@Autowired
	private SpecialOfferRepository specialOfferRepository;

	/*
	 * VIEW LISTA OFFERTE
	 */

	@GetMapping("/offerte/lista_offerte")
	public String listaOfferte(Model model) {
		List<SpecialOffer> specialOffer = specialOfferRepository.findAll();
		model.addAttribute("list", specialOffer);
		return "/offerte/lista_offerte";
	}

	/*
	 * DELETE DELLE OFFERTE
	 */

	@PostMapping("/offerte/lista_offerte/{id}")
	public String deleteSpecialOffer(@PathVariable("id") Long id) {
		specialOfferRepository.deleteById(id);
		return "redirect:/offerte/lista_offerte";
	}

	/*
	 * CREAZIONE NUOVA OFFERTA
	 */

	@GetMapping("/offerte/nuova_offerta")
	public String creaSpecialOffer(Model model) {
		model.addAttribute("specialOffer", new SpecialOffer());
		return "offerte/nuova_offerta";
	}

	@PostMapping("/offerte/nuova_offerta")
	public String storeSpecialOffer(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "offerte/nuova_offerta";
		}
		specialOfferRepository.save(formSpecialOffer);
		return "redirect:/offerte/lista_offerte";

	}

	/*
	 * EDIT OFFERTE
	 */

	@GetMapping("/offerte/edit_offerta/{id}")
	public String editSpecialOffer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("specialOffer", specialOfferRepository.findById(id).get());
		return "offerte/edit_offerta";
	}

	@PostMapping("/offerte/edit_offerta/{id}")
	public String updateSpecialOffer(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOffer,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "/offerte/edit_offerta";
		}
		specialOfferRepository.save(specialOffer);
		return "redirect:/offerte/lista_offerte";
	}

}

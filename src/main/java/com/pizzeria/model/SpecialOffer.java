package com.pizzeria.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "special_offer")
public class SpecialOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Il nome non può essere Null")
	@Size(max = 50, message = "Il nome può avere massimo 50 caratteri")
	@Column(nullable = false)
	private String title;

	@NotNull(message = "lo sconto non può essere null")
	private int sconto;

	@NotNull(message = "La data di inizio non può essere null")
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@NotNull(message = "La data di fine non può essere null")
	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	// RELAZIONE FK
	@ManyToOne
	@JoinColumn(name = "pizza_id")
	private Pizza pizza;

	public SpecialOffer(String title, int sconto, LocalDate startDate, LocalDate endDate, Pizza pizza) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pizza = pizza;
	}

	public SpecialOffer() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	// get e set del JOIN COLUMN
	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	@Override
	public String toString() {
		return "SpecialOffer [id=" + id + ", title=" + title + ", sconto=" + sconto + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", pizza=" + pizza + "]";
	}

}

package com.pizzeria.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "La data di inizio non può essere null")
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "La data di fine non può essere null")
	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	public SpecialOffer(String title, int sconto, LocalDate startDate, LocalDate endDate) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public SpecialOffer(int sconto) {
		super();
		this.sconto = sconto;
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

	// RELAZIONI con relativi get e set

	@OneToMany(mappedBy = "specialOffer")
	private List<Pizza> pizzas;


	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	@Override
	public String toString() {
		return "SpecialOffer [id=" + id + ", title=" + title + ", sconto=" + sconto + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

}

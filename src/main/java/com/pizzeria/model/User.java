package com.pizzeria.model;

import java.util.List;

import javax.management.relation.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

//@Entity
public class User {

	@Id
	private Integer id;
	
	private String password;
	
	private String username;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
}

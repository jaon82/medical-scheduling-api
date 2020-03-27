package com.medicalscheduling.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Customer extends Person {
	@Column(nullable = false)
	private String telefone;

	@OneToMany(mappedBy = "customer")
	private List<Appointment> appointments;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}

package com.medicalscheduling.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Customer extends Person {
	public Customer() {
		super();
	}

	public Customer(String username, String email) {
		super(username, email);
	}

	@Column(nullable = false)
	private String cpf;

	private String telefone;

	@OneToMany(mappedBy = "customer")
	private List<Appointment> appointments;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}

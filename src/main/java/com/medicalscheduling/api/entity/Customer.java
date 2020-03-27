package com.medicalscheduling.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends Person {
	@Column(nullable = false)
	private String telefone;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}

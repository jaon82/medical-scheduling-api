package com.medicalscheduling.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Doctor extends Person {
	public Doctor(String username, String email) {
		super(username, email);
	}

	@Column(nullable = false)
	private String crm;

	@OneToMany(mappedBy = "doctor")
	private List<Appointment> appointments;

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
}

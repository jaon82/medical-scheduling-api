package com.medicalscheduling.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Doctor extends Person {
	@Column(nullable = false)
	private String crm;

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
}

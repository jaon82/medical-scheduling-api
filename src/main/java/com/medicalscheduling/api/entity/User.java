package com.medicalscheduling.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends Person {
	@Column(nullable = false)
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

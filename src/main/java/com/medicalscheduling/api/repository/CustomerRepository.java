package com.medicalscheduling.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicalscheduling.api.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Boolean existsByEmail(String email);
	Boolean existsByCpf(String cpf);
}

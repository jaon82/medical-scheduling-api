package com.medicalscheduling.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicalscheduling.api.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Boolean existsByEmail(String email);
	Boolean existsByCrm(String crm);
}
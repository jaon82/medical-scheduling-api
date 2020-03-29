package com.medicalscheduling.api.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicalscheduling.api.entity.Appointment;
import com.medicalscheduling.api.entity.Customer;
import com.medicalscheduling.api.entity.Doctor;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	Optional<Appointment> findFirstByDoctorAndCustomerAndDate(Doctor doctor, Customer customer, Date date);
}

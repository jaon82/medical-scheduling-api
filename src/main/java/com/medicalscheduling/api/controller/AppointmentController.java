package com.medicalscheduling.api.controller;

import java.util.List;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medicalscheduling.api.entity.Appointment;
import com.medicalscheduling.api.entity.Customer;
import com.medicalscheduling.api.entity.Doctor;
import com.medicalscheduling.api.repository.AppointmentRepository;
import com.medicalscheduling.api.repository.CustomerRepository;
import com.medicalscheduling.api.repository.DoctorRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AppointmentController {
	@Autowired
	private AppointmentRepository _appointmentRepository;

	@Autowired
	private DoctorRepository _doctorRepository;

	@Autowired
	private CustomerRepository _customerRepository;

	@RequestMapping(value = "/appointment", method = RequestMethod.GET)
	public List<Appointment> Get() {
		return _appointmentRepository.findAll();
	}

	@RequestMapping(value = "/appointment/doctor/{doctorId}/customer/{customerId}", method = RequestMethod.POST)
	public Appointment Post(@PathVariable(value = "doctorId") Long doctorId,
			@PathVariable(value = "customerId") Long customerId, @Valid @RequestBody Appointment appointment)
			throws RelationNotFoundException {

		Optional<Doctor> doctorO = _doctorRepository.findById(doctorId);
		if (!doctorO.isPresent())
			throw new RelationNotFoundException("Doctor with id " + doctorId + " does not exist");

		Optional<Customer> customerO = _customerRepository.findById(customerId);
		if (!customerO.isPresent())
			throw new RelationNotFoundException("Customer with id " + customerId + " does not exist");

		Doctor doctor = doctorO.get();
		Customer customer = customerO.get();
		appointment.setDoctor(doctor);
		appointment.setCustomer(customer);
		
		//Example<Appointment> example = Example.of(appointment);		 
	    //Optional<Appointment> appointmentO = _appointmentRepository.findOne(example);

		//Optional<Appointment> appointmentO = _appointmentRepository.findOne(appointment);
	    //List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);
	    Optional<Appointment> appointmentO = _appointmentRepository.findFirstByDoctorAndCustomerAndDate(doctor, customer, appointment.getDate());
		if (appointmentO.isPresent())
			throw new RelationNotFoundException("Appointment already exists for this data.");

		return _appointmentRepository.save(appointment);
	}

	@RequestMapping(value = "/appointment/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Appointment> appointment = _appointmentRepository.findById(id);
		if (appointment.isPresent()) {
			_appointmentRepository.delete(appointment.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
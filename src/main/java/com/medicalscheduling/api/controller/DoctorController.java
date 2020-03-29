package com.medicalscheduling.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medicalscheduling.api.entity.Doctor;
import com.medicalscheduling.api.repository.DoctorRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DoctorController {
	@Autowired
	private DoctorRepository _doctorRepository;

	@RequestMapping(value = "/doctor", method = RequestMethod.GET)
	public List<Doctor> Get() {
		return _doctorRepository.findAll();
	}

	@RequestMapping(value = "/doctor/{id}", method = RequestMethod.GET)
	public ResponseEntity<Doctor> GetById(@PathVariable(value = "id") long id) {
		Optional<Doctor> doctor = _doctorRepository.findById(id);
		if (doctor.isPresent())
			return new ResponseEntity<Doctor>(doctor.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/doctor", method = RequestMethod.POST)
	public Doctor Post(@Valid @RequestBody Doctor doctor) {
		return _doctorRepository.save(doctor);
	}

	@RequestMapping(value = "/doctor/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Doctor> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Doctor newDoctor) {
		Optional<Doctor> oldDoctor = _doctorRepository.findById(id);
		if (oldDoctor.isPresent()) {
			Doctor doctor = oldDoctor.get();
			doctor.setName(newDoctor.getName());
			doctor.setEmail(newDoctor.getEmail());
			doctor.setCrm(newDoctor.getCrm());
			_doctorRepository.save(doctor);
			return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/doctor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Doctor> doctor = _doctorRepository.findById(id);
		if (doctor.isPresent()) {
			_doctorRepository.delete(doctor.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
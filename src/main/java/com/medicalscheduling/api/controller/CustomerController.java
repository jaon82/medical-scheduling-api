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

import com.medicalscheduling.api.entity.Customer;
import com.medicalscheduling.api.payload.response.MessageResponse;
import com.medicalscheduling.api.repository.CustomerRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository _customerRepository;

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public List<Customer> Get() {
		return _customerRepository.findAll();
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> GetById(@PathVariable(value = "id") long id) {
		Optional<Customer> customer = _customerRepository.findById(id);
		if (customer.isPresent())
			return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<?> Post(@Valid @RequestBody Customer customer) {
		if (_customerRepository.existsByEmail(customer.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}		
		if (_customerRepository.existsByCpf(customer.getCpf())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: CPF is already in use!"));
		}		
		return ResponseEntity.ok(_customerRepository.save(customer));
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Customer newCustomer) {
		Optional<Customer> oldCustomer = _customerRepository.findById(id);
		if (oldCustomer.isPresent()) {
			Customer customer = oldCustomer.get();
			customer.setName(newCustomer.getName());
			customer.setEmail(newCustomer.getEmail());
			customer.setTelefone(newCustomer.getTelefone());
			_customerRepository.save(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Customer> customer = _customerRepository.findById(id);
		if (customer.isPresent()) {
			_customerRepository.delete(customer.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
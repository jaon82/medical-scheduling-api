package com.medicalscheduling.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medicalscheduling.api.entity.User;
import com.medicalscheduling.api.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository _userRepository;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> Get() {
		return _userRepository.findAll();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> GetById(@PathVariable(value = "id") long id) {
		Optional<User> user = _userRepository.findById(id);
		if (user.isPresent())
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User Post(@Valid @RequestBody User user) {
		return _userRepository.save(user);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> Put(@PathVariable(value = "id") long id, @Valid @RequestBody User newUser) {
		Optional<User> oldUser = _userRepository.findById(id);
		if (oldUser.isPresent()) {
			User user = oldUser.get();
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			user.setPassword(newUser.getPassword());
			_userRepository.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<User> user = _userRepository.findById(id);
		if (user.isPresent()) {
			_userRepository.delete(user.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
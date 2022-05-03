package api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.models.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")

public class UserAllController {
     
    @Autowired
	UserRepository userRepository;

	@GetMapping("/parents")
	public ResponseEntity<List<Parent>> getAllParents(@RequestParam(required = false) String firstName) {
		try {
			List<Parent> parents = new ArrayList<Parent>();
			if (firstName == null)
				userRepository.findAll().forEach(parents::add);
			else
				userRepository.findByLastname(firstName).forEach(parents::add);
			if (parents.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(parents, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 


	@GetMapping("/parents/{id}")
	public ResponseEntity<Parent> getParentById(@PathVariable("id") UUID id) {
		Optional<Parent> parentData = userRepository.findById(id);
		if (parentData.isPresent()) {
			return new ResponseEntity<>(parentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/parents")
	public ResponseEntity<Parent> createParent(@RequestBody Parent parent) {
		try {
			Parent _parent = userRepository
					.save(new Parent(parent.getId(), parent.getFirstname(), parent.getLastname(), parent.getPassword(), parent.getEmail(), parent.getAddress(), parent.getPhoneNumber()) );
			return new ResponseEntity<>(_parent, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        
		}
	}
	@PutMapping("/parents/{id}")
	public ResponseEntity<Parent> updateUser(@PathVariable("id") UUID id, @RequestBody Parent parent) {
		Optional<Parent> parentData = userRepository.findById(id);
		if (parentData.isPresent()) {
			Parent _parent = parentData.get();
			_parent.setFirstname(parent.getFirstname());
			_parent.setLastname(parent.getLastname());
			_parent.setAddress(parent.getAddress());
			return new ResponseEntity<>(userRepository.save(_parent), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/parents/{id}")
	public ResponseEntity<HttpStatus> deleteParent(@PathVariable("id") UUID id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/parents")
	public ResponseEntity<HttpStatus> deleteAllparents() {
		try {
			userRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/* @GetMapping("/parents/lastname")
	public ResponseEntity<List<Parent>> findByLastname() {
		try {
			List<Parent> parents = userRepository.findByName(String);
			if (parents.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(parents, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/


}

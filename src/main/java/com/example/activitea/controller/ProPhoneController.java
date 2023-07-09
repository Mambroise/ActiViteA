package com.example.activitea.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitea.entity.ProPhone;
import com.example.activitea.service.ProPhoneService;

@RestController
public class ProPhoneController {

	@Autowired
	private ProPhoneService proPhoneService;
	
	//Crud create a new Pro phone number
	@PostMapping("/prophone")
	public ResponseEntity<String> create(@RequestBody ProPhone proPhone){
		if (proPhoneService.create(proPhone)) {
			return new ResponseEntity<String>("Le numéro "+proPhone.getPhone()+" a bien été enregistré",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Le numéro "+proPhone.getPhone()+" n'a pas pu être enregistré",HttpStatus.BAD_REQUEST);
		}
	}

	//Crud Read the user addresses to select one 
	@GetMapping("/prophone/{id}")
	public List<ProPhone> readProPhone(@PathVariable("id") int userId){
		return  proPhoneService.readByUserId(userId);
	}
		
	// Find an address by id before update it or other possible action
	@GetMapping("/update/prophone/{id}")
	public Optional<ProPhone> findEmailById(@PathVariable("id") int proPhoneId){
		return  proPhoneService.findById(proPhoneId);
	}
	
	//Crud Delete the selected email from the user account
	@DeleteMapping("/prophone/{id}")
	public ResponseEntity<String> deleteProEmail(@PathVariable("id") int proPhoneId){
		if (proPhoneService.deleteProPhone(proPhoneId)) {
			return new ResponseEntity<String>("Le numéro a bien été supprimé",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Le numéro n'a pas pu être supprimé",HttpStatus.BAD_REQUEST);
		}
	}
}

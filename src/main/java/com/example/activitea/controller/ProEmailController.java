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

import com.example.activitea.entity.ProEmail;
import com.example.activitea.service.ProEmailService;

@RestController
public class ProEmailController {
	@Autowired
	public ProEmailService proEmailService;

	
	//Crud Create a new email
	@PostMapping("/proemail")
	public ResponseEntity<String> create(@RequestBody ProEmail proEmail){
		if (proEmailService.create(proEmail)) {
			return new ResponseEntity<String>("L'email "+proEmail.getEmail()+" a bien été enregistré",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("L'email "+proEmail.getEmail()+" existe déjà",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Read the user emails to select one 
	@GetMapping("/proemail/{id}")
	public List<ProEmail> readProEmail(@PathVariable("id") int userId){
		return  proEmailService.readByUserId(userId);
	}
	
	// Find an email by id before update it or other possible action
	@GetMapping("/update/proemail/{id}")
	public Optional<ProEmail> findEmailById(@PathVariable("id") int emailId){
		return  proEmailService.findById(emailId);
	}
	
	
	//Crud Delete the selected email from the user account
	@DeleteMapping("/proemail/{id}")
	public ResponseEntity<String> deleteProEmail(@PathVariable("id") int emailId){
		if (proEmailService.deleteEmail(emailId)) {
			return new ResponseEntity<String>("L'email a bien été supprimé",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("L'email n'a pas pu être supprimé",HttpStatus.BAD_REQUEST);
		}
	}
}

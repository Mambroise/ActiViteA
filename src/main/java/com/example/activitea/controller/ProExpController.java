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

import com.example.activitea.entity.ProExp;
import com.example.activitea.service.ProExpService;

@RestController
public class ProExpController {
	
	@Autowired
	public ProExpService proExpService;

	
	//Crud Create a new skill
	@PostMapping("/proexp")
	public ResponseEntity<String> create(@RequestBody ProExp proExp){
		if (proExpService.create(proExp)) {
			return new ResponseEntity<String>("Votre expérience dans la société "+proExp.getCompany()+" a bien été enregistrée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Votre expérience dans la société "+proExp.getCompany()+" n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Read the user skill to select one 
	@GetMapping("/proexp/{id}")
	public List<ProExp> readProEmail(@PathVariable("id") int userId){
		return  proExpService.readByUserId(userId);
	}
	
	// Find a skill by id before update it or other possible action
	@GetMapping("/update/proexp/{id}")
	public Optional<ProExp> findEmailById(@PathVariable("id") int proExpId){
		return  proExpService.findById(proExpId);
	}
	
	
	//Crud Delete the selected skill from the user account
	@DeleteMapping("/proexp/{id}")
	public ResponseEntity<String> deleteSproExp(@PathVariable("id") int proExpId){
		if (proExpService.deleteProExp(proExpId)) {
			return new ResponseEntity<String>("L'expérience a bien été supprimée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("L'expérience n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
}

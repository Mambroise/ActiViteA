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

import com.example.activitea.entity.LifeExp;
import com.example.activitea.service.LifeExpService;

@RestController
public class LifeExpController {
	
	@Autowired
	public LifeExpService lifeExpService;

	
	//Crud Create a new skill
	@PostMapping("/lifeexp")
	public ResponseEntity<String> create(@RequestBody LifeExp lifeExp){
		if (lifeExpService.create(lifeExp)) {
			return new ResponseEntity<String>("L'expérience suivante: "+lifeExp.getContent()+" a bien été enregistrée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("L'expérience suivante: "+lifeExp.getContent()+" n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Read the user skill to select one 
	@GetMapping("/lifeexp/{id}")
	public List<LifeExp> readLifeExp(@PathVariable("id") int userId){
		return  lifeExpService.readByUserId(userId);
	}
	
	// Find a skill by id before update it or other possible action
	@GetMapping("/update/lifeexp/{id}")
	public Optional<LifeExp> findEmailById(@PathVariable("id") int lifeExpId){
		return  lifeExpService.findById(lifeExpId);
	}
	
	
	//Crud Delete the selected skill from the user account
	@DeleteMapping("/lifeexp/{id}")
	public ResponseEntity<String> deleteLifeExp(@PathVariable("id") int lifeExpId){
		if (lifeExpService.deleteLifeExp(lifeExpId)) {
			return new ResponseEntity<String>("L'expérience a bien été supprimée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("L'expérience n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}

}

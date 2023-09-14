package com.example.activitea.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.activitea.Dto.LifeExpDto;
import com.example.activitea.service.LifeExpService;

@RestController
@CrossOrigin("http://localhost:3000")
public class LifeExpController {
	
	@Autowired
	public LifeExpService lifeExpService;

	
	//Crud Create a new skill
	@PostMapping("/lifeexp")
	public ResponseEntity<String> create(@RequestBody LifeExpDto lifeExpDto){
		if (lifeExpService.create(lifeExpDto)) {
			return new ResponseEntity<String>("L'expérience suivante: "+lifeExpDto.getContent()+" a bien été enregistrée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("L'expérience suivante: "+lifeExpDto.getContent()+" n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Read the user skill to select one 
	@GetMapping("/lifeexp/{id}")
	public List<LifeExpDto> readLifeExp(@PathVariable("id") int userId){
		return  lifeExpService.readByUserId(userId);
	}
	
	// Find a skill by id before update it or other possible action
	@GetMapping("/updatelifeexp/{id}")
	public LifeExpDto findLifeExpById(@PathVariable("id") int lifeExpId){
		return  lifeExpService.findById(lifeExpId);
	}
	
	//Crud update the language
	@PutMapping("/lifeexp/{id}")
	public ResponseEntity<String> updateLanguage(@PathVariable("id") int lifeExpId,@RequestBody LifeExpDto lifeExpDto){
		if (lifeExpService.updateLifeExp(lifeExpId, lifeExpDto)) {
			 return new ResponseEntity<>("L'expérience a bien été mis à jour", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("L'expérience n'a pas pu être mis à jour", HttpStatus.BAD_REQUEST);
		}
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

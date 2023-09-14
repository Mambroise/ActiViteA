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

import com.example.activitea.Dto.ProExpDto;
import com.example.activitea.service.ProExpService;

@RestController
@CrossOrigin("http://localhost:3000")
public class ProExpController {
	
	@Autowired
	public ProExpService proExpService;

	
	//Crud Create a new experience
	@PostMapping("/proexp")
	public ResponseEntity<String> create(@RequestBody ProExpDto proExpDto){
		if (proExpService.create(proExpDto)) {
			return new ResponseEntity<String>("Votre expérience dans la société "+proExpDto.getCompany()+" a bien été enregistrée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Votre expérience dans la société "+proExpDto.getCompany()+" n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Read the user experience to select one 
	@GetMapping("/proexp/{id}")
	public List<ProExpDto> readProExp(@PathVariable("id") int userId){
		return  proExpService.readByUserId(userId);
	}
	
	// Find a experience by id before update it or other possible action
	@GetMapping("/updateproexp/{id}")
	public ProExpDto findExpById(@PathVariable("id") int proExpId){
		return  proExpService.findById(proExpId);
	}
	
	//Crud update the pro experience
		@PutMapping("/proexp/{id}")
		public ResponseEntity<String> updateProExp(@PathVariable("id") int proExpId, @RequestBody ProExpDto proExpDto){
			if (proExpService.updateProExp(proExpId, proExpDto)) {
				 return new ResponseEntity<>("Le numéro a bien été mis à jour", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("Le numéro n'a pas pu être mis à jour", HttpStatus.BAD_REQUEST);
			}
		}
	
	//Crud Delete the selected experience from the user account
	@DeleteMapping("/proexp/{id}")
	public ResponseEntity<String> deleteProExp(@PathVariable("id") int proExpId){
		if (proExpService.deleteProExp(proExpId)) {
			return new ResponseEntity<String>("L'expérience a bien été supprimée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("L'expérience n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
	

}

package com.example.activitea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitea.Dto.CursusDto;
import com.example.activitea.service.CursusService;

@RestController
public class CursusController {

	@Autowired
	public CursusService cursusService;

	
	//Crud Create a new skill
	@PostMapping("/cursus")
	public ResponseEntity<String> create(@RequestBody CursusDto cursusDto){
		if (cursusService.create(cursusDto)) {
			return new ResponseEntity<String>("Le diplome suivant: "+cursusDto.getDiploma()+" a bien été enregistrée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Le diplome suivant: "+cursusDto.getDiploma()+" n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Read the user skill to select one 
	@GetMapping("/cursus/{id}")
	public List<CursusDto> readCursus(@PathVariable("id") int userId){
		return  cursusService.readByUserId(userId);
	}
	
	// Find a skill by id before update it or other possible action
	@GetMapping("/updatecursus/{id}")
	public CursusDto findEmailById(@PathVariable("id") int cursusId){
		return  cursusService.findById(cursusId);
	}
	
	//Crud Update the selected Cursus
	@PutMapping("/cursus/{id}")
	public ResponseEntity<String> updateCursus(@PathVariable("id") int cursusId, @RequestBody CursusDto cursusDto) {
	    if (cursusService.updateCursus(cursusId, cursusDto)) {
	        return new ResponseEntity<>("Le diplome a bien été mis à jour", HttpStatus.ACCEPTED);
	    } else {
	        return new ResponseEntity<>("Le diplome n'a pas pu être mis à jour", HttpStatus.BAD_REQUEST);
	    }
	}
	
	//Crud Delete the selected skill from the user account
	@DeleteMapping("/cursus/{id}")
	public ResponseEntity<String> deleteCursus(@PathVariable("id") int cursusId){
		if (cursusService.deleteCursus(cursusId)) {
			return new ResponseEntity<String>("Le diplome a bien été supprimé",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Le diplome n'a pas pu être supprimé",HttpStatus.BAD_REQUEST);
		}
	}
}

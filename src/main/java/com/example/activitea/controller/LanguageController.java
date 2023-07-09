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

import com.example.activitea.Dto.LanguageDto;
import com.example.activitea.service.LanguageService;

@RestController
public class LanguageController {


	@Autowired
	public LanguageService languageService;

	
	//Crud Create a new language
	@PostMapping("/language")
	public ResponseEntity<String> create(@RequestBody LanguageDto languageDto){
		if (languageService.create(languageDto)) {
			return new ResponseEntity<String>("La langue suivante: "+languageDto.getLanguage()+" a bien été enregistrée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("La langue suivante: "+languageDto.getLanguage()+" n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Read the user language to select one 
	@GetMapping("/language/{id}")
	public List<LanguageDto> readLanguage(@PathVariable("id") int userId){
		return  languageService.readByUserId(userId);
	}
	
	// Find a language by id before update it or other possible action
	@GetMapping("/updatelanguage/{id}")
	public LanguageDto findEmailById(@PathVariable("id") int languageId){
		return  languageService.findById(languageId);
	}
	
	//Crud update the language
	@PutMapping("/language/{id}")
	public ResponseEntity<String> updateLanguage(@PathVariable("id") int languageId,@RequestBody LanguageDto languageDto){
		if (languageService.updateCursus(languageId, languageDto)) {
			 return new ResponseEntity<>("La langue a bien été mis à jour", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("La langue a bien été mis à jour", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//Crud Delete the selected skill from the user account
	@DeleteMapping("/language/{id}")
	public ResponseEntity<String> deleteLanguage(@PathVariable("id") int languageId){
		if (languageService.deleteLanguage(languageId)) {
			return new ResponseEntity<String>("La langue a bien été supprimé",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("La langue n'a pas pu être supprimé",HttpStatus.BAD_REQUEST);
		}
	}
}

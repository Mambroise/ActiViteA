package com.example.activitea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.activitea.Dto.CoverLetterDto;
import com.example.activitea.service.CoverLetterService;

@Controller
public class CoverLetterController {

	@Autowired
	private CoverLetterService coverLetterService;
	
	//Crud create a new coverLetter
		@PostMapping("/coverletter")
		public ResponseEntity<String> create(@RequestBody CoverLetterDto coverLetterDto){
			if (coverLetterService.create(coverLetterDto)) {
				return new ResponseEntity<String>("",HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("",HttpStatus.BAD_REQUEST);
			}
		}

		//Crud Read the user coverletter to select one 
		@GetMapping("/coverletter/{id}")
		public List<CoverLetterDto> readCoverLetter(@PathVariable("id") int userId){
			return  coverLetterService.readByUserId(userId);
		}
			
		// Find a coverletter by id before update it or other possible action
		@GetMapping("/updatecoverletter/{id}") 
		public CoverLetterDto findEmailById(@PathVariable("id") int coverLetterId){
			return  coverLetterService.findById(coverLetterId);
		}
		
		//Crud update the coverletter
		@PutMapping("/coverletter/{id}")
		public ResponseEntity<String> updateCoverLetter(@RequestBody CoverLetterDto coverLetterDto){
			if (coverLetterService.updateCoverLetter(coverLetterDto)) {
				 return new ResponseEntity<>("La letter de motivation a bien été mis à jour", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("La letter de motivation n'a pas pu être mis à jour", HttpStatus.BAD_REQUEST);
			}
		}
		
		//Crud Delete the selected coverletter from the user account
		@DeleteMapping("/coverletter/{id}")
		public ResponseEntity<String> deleteProEmail(@PathVariable("id") int coverLetterId){
			if (coverLetterService.deleteCoverLetter(coverLetterId)) {
				return new ResponseEntity<String>("La lettre de motivation a bien été supprimée",HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("La lettre de motivation n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
			}
		}
}

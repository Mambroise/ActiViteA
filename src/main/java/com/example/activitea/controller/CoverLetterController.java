package com.example.activitea.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.activitea.entity.CoverLetter;
import com.example.activitea.service.CoverLetterService;

@Controller
public class CoverLetterController {

	@Autowired
	private CoverLetterService coverLetterService;
	
	//Crud create a new address
		@PostMapping("/coverletter")
		public ResponseEntity<String> create(@RequestBody CoverLetter coverLetter){
			if (coverLetterService.create(coverLetter)) {
				return new ResponseEntity<String>("",HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("",HttpStatus.BAD_REQUEST);
			}
		}

		//Crud Read the user addresses to select one 
		@GetMapping("/coverletter/{id}")
		public List<CoverLetter> readCoverLetter(@PathVariable("id") int userId){
			return  coverLetterService.readByUserId(userId);
		}
			
		// Find an address by id before update it or other possible action
		@GetMapping("/update/coverletter/{id}")
		public Optional<CoverLetter> findEmailById(@PathVariable("id") int coverLetterId){
			return  coverLetterService.findById(coverLetterId);
		}
		
		//Crud Delete the selected email from the user account
		@DeleteMapping("/coverletter/{id}")
		public ResponseEntity<String> deleteProEmail(@PathVariable("id") int coverLetterId){
			if (coverLetterService.deleteCoverLetter(coverLetterId)) {
				return new ResponseEntity<String>("La lettre de motivation a bien été supprimée",HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("La lettre de motivation n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
			}
		}
}

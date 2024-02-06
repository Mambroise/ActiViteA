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

import com.example.activitea.Dto.ProEmailDto;
import com.example.activitea.entity.ValidationResult;
import com.example.activitea.service.ProEmailService;

@RestController
@CrossOrigin("http://localhost:3000")
public class ProEmailController {
	@Autowired
	public ProEmailService proEmailService;

	
	//Crud Create a new email
	@PostMapping("/proemail")
	public ResponseEntity<String> create(@RequestBody ProEmailDto proEmailDto){
		ValidationResult result = proEmailService.create(proEmailDto);
		if (result.isSuccess()) {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Read the user emails to select one 
	@GetMapping("/proemail/{id}")
	public List<ProEmailDto> readProEmail(@PathVariable("id") int userId){
		return  proEmailService.readByUserId(userId);
	}
	
	// Find an email by id before update it or other possible action
	@GetMapping("/updateproemail/{id}")
	public ProEmailDto findEmailById(@PathVariable("id") int emailId){
		return  proEmailService.findById(emailId);
	}
	
	//Crud update the ProEmail
	@PutMapping("/proemail/{id}")
	public ResponseEntity<String> updateProEmail(@PathVariable("id") int emailId,@RequestBody ProEmailDto proEmailDto){
		if (proEmailService.updateProEmail(emailId,proEmailDto)) {
			 return new ResponseEntity<>("L'email a bien été mis à jour", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("L'email n'a pas pu être mis à jour", HttpStatus.BAD_REQUEST);
		}
		
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

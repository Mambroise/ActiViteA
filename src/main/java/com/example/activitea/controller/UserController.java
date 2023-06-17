package com.example.activitea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitea.entity.User;
import com.example.activitea.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> create(@RequestBody User user){
		if (userService.createUser(user)) {
			return new ResponseEntity<String>(user.getName()+" "+user.getFirstname()+" a bien été créé",HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(user.getName()+" "+user.getFirstname()+" n'a pu être créé",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/")
	public String getmessage() {
		return "Coucou tu gères";
		
	}
	
}

package com.example.activitea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitea.Dto.PasswordDto;
import com.example.activitea.Dto.UserDto;
import com.example.activitea.entity.User;
import com.example.activitea.service.UserService;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;
	
	//Crud C
	@PostMapping("/register")
	public ResponseEntity<String> create(@RequestBody UserDto userDto){
		if (userService.createUser(userDto)) {
			return new ResponseEntity<String>(userDto.getName()+" "+userDto.getFirstName()+" a bien été créé",HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(userDto.getName()+" "+userDto.getFirstName()+" n'a pu être créé",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Read the user pour le mode admin
	@GetMapping("/admin")
	public List<User> read(){
		return userService.readUser();
	}
	
	//Crud Read the user pour le mode admin
	@GetMapping("/user/{id}")
	public UserDto findUserById(@PathVariable("id") int userId){
		return userService.findById(userId);
	}
	
	//Crud Update the user, except password
	@PutMapping("/updateuser/{id}")
	public ResponseEntity<String> updateUser(@PathVariable("id") int userId,@RequestBody UserDto userDto){
		System.err.println("coucou dans change password");
		if (userService.updateUser(userId,userDto)) {
			return new ResponseEntity<String>(userDto.getName()+" "+userDto.getFirstName()+" a bien été mis à jour",HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(userDto.getName()+" "+userDto.getFirstName()+" n'a pu être mis à jour",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Update the user password
	@PutMapping("/updatepassword/{id}")
	public ResponseEntity<String> updateUserPassword(@PathVariable("id") int userId,@RequestBody PasswordDto passwordDto){
		if (userService.changePassword(userId,passwordDto)) {
			return new ResponseEntity<String>("Votre mot de passe a bien été mis à jour",HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Votre mot de passe n'a pas pu être mis à jour",HttpStatus.BAD_REQUEST);
		}
	}
}

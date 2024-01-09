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
import com.example.activitea.entity.ValidationResult;
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
		if (userService.updateUser(userId,userDto)) {
			return new ResponseEntity<String>(userDto.getName()+" "+userDto.getFirstName()+" a bien été mis à jour",HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(userDto.getName()+" "+userDto.getFirstName()+" n'a pu être mis à jour",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Update the user password
	@PutMapping("/updatepassword/{id}")
	public ResponseEntity<String> updateUserPassword(@PathVariable("id") int userId,@RequestBody PasswordDto passwordDto){
		ValidationResult result = userService.changePassword(userId,passwordDto);
		if (result.isSuccess()) {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	//Function to check wether the user has saved data
	@GetMapping("/userdata/{id}")
	public ResponseEntity<String> checkData(@PathVariable("id") int userId){
		ValidationResult result = userService.dataControl(userId);
		
		if (result.isSuccess()) {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.BAD_REQUEST);
		}	
	}
}

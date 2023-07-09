package com.example.activitea.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.activitea.entity.Address;
import com.example.activitea.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	//Crud create a new address
	@PostMapping("/address")
	public ResponseEntity<String> create(@RequestBody Address address){
		if (addressService.create(address)) {
			return new ResponseEntity<String>("L'adresse a bien été enregistrée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("L'adresse n'a pas pu être enregistrée",HttpStatus.BAD_REQUEST);
		}
	}

	//Crud Read the user addresses to select one 
	@GetMapping("/address/{id}")
	public List<Address> readAdress(@PathVariable("id") int userId){
		return  addressService.readByUserId(userId);
	}
		
	// Find an address by id before update it or other possible action
	@GetMapping("/update/address/{id}")
	public Optional<Address> findEmailById(@PathVariable("id") int addressId){
		return  addressService.findById(addressId);
	}
	
	//Crud Delete the selected email from the user account
	@DeleteMapping("/address/{id}")
	public ResponseEntity<String> deleteProEmail(@PathVariable("id") int addressId){
		if (addressService.deleteAddress(addressId)) {
			return new ResponseEntity<String>("L'adresse a bien été supprimée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("L'adresse n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
}

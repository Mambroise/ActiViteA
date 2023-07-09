package com.example.activitea.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitea.entity.Address;
import com.example.activitea.repository.AddressRepository;

@RestController
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	//Crud create a new address which will figure on the the coverLetter
		public boolean create(Address address) {
			if(addressRepository.save(address)!=null) {
				return true;
			}else {
				return false;
			}
		}
		
		//Crud Read and display all addresses from a User
		public List<Address> readByUserId(int userId) {
			return addressRepository.findByUserId(userId);
		}
		
		//fin by id and display the selected email 
		public Optional<Address> findById(int addressId) {
			return addressRepository.findById(addressId);
		}
		
		//Crud Delete the selected pro Email
		public boolean deleteAddress(int addressId) {
			addressRepository.deleteById(addressId);
			return addressRepository.findById(addressId).isEmpty() ? true :  false ;
		}
}

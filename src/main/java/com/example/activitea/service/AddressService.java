package com.example.activitea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitea.Dto.AddressDto;
import com.example.activitea.entity.Address;
import com.example.activitea.entity.User;
import com.example.activitea.repository.AddressRepository;

@RestController
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	//Crud create a new address which will figure on the the coverLetter
		public boolean create(AddressDto addressDto) {
			return addressRepository.save(convertDtoToEntity(addressDto))!=null ? true : false;
		}
		
		//Crud Read and display all addresses from a User
		public List<AddressDto> readByUserId(int userId) {
			return addressRepository.findByUserId(userId).stream().map(this::convertEntityToDto).collect(Collectors.toList());
		}
		
		//fin by id and display the selected address 
		public AddressDto findById(int addressId) {
			return convertEntityToDto(addressRepository.findById(addressId).get()) ;
		}
		
		//Crud Delete the selected address
		public boolean deleteAddress(int addressId) {
			addressRepository.deleteById(addressId);
			return addressRepository.findById(addressId).isEmpty() ? true :  false ;
		}
		
		//Crud Update the address
		public boolean updateAddress(int addressId, AddressDto addressDto) {
			 Optional<Address> optionalAddress = addressRepository.findById(addressId);
			    
			    if (optionalAddress.isPresent()) {
			        Address address = optionalAddress.get();
			        address.setNumber(addressDto.getNumber());
			        address.setStreet(addressDto.getStreet());
			        address.setZipCode(addressDto.getZipCode());
			        address.setCity(addressDto.getCity());
			        addressRepository.save(address); // Save modifications
			        return true;
			    } else {
			        return false;
			    }
			}
			
		//Method to convert Dto to entity
		public Address convertDtoToEntity(AddressDto addressDto) {
		Address address=new Address();
		User user=new User();
		user.setId(addressDto.getUserId());
		address.setNumber(addressDto.getNumber());
		address.setStreet(addressDto.getStreet().trim());
		address.setZipCode(addressDto.getZipCode());
		address.setCity(addressDto.getCity());
		address.setUser(user);
		return address;
		}
		
		//Method to convert entity to Dto
		public AddressDto convertEntityToDto(Address address) {
			AddressDto addressDto=new AddressDto();
			addressDto.setId(address.getId());
			addressDto.setNumber(address.getNumber());
			addressDto.setStreet(address.getStreet());
			addressDto.setZipCode(address.getZipCode());
			addressDto.setCity(address.getCity());
			addressDto.setUserId(address.getUser().getId());
			addressDto.setFirstName(address.getUser().getFirstname());
			addressDto.setName(address.getUser().getName());
			addressDto.setEmail(address.getUser().getEmail());
			return addressDto;
		}
}

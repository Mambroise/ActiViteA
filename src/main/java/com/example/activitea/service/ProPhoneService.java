package com.example.activitea.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.entity.ProPhone;
import com.example.activitea.repository.ProPhoneRepository;

@Service
public class ProPhoneService {

	@Autowired
	private ProPhoneRepository proPhoneRepository;
	
	//Crud create a new Pro Phone number which will figure on the the coverLetter
		public boolean create(ProPhone proPhone) {
			if(proPhoneRepository.save(proPhone)!=null) {
				return true;
			}else {
				return false;
			}
		}
		
		//Crud Read and display all pro phone numbers from a User
		public List<ProPhone> readByUserId(int userId) {
			return proPhoneRepository.findByUserId(userId);
		}
		
		//fin by id and display the selected email 
		public Optional<ProPhone> findById(int proPhoneId) {
			return proPhoneRepository.findById(proPhoneId);
		}
		
		//Crud Delete the selected pro Email
		public boolean deleteProPhone(int proPhoneId) {
			proPhoneRepository.deleteById(proPhoneId);
			return proPhoneRepository.findById(proPhoneId).isEmpty() ? true :  false ;
		}
}

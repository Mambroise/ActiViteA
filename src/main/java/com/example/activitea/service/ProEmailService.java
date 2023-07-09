package com.example.activitea.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.ProEmailDto;
import com.example.activitea.entity.ProEmail;
import com.example.activitea.entity.User;
import com.example.activitea.repository.ProEmailRepository;

@Service
public class ProEmailService {
	@Autowired
	public ProEmailRepository proEmailRepository;

	//Crud create a new professionnal email which will figure on the the coverLetter
	public boolean create(ProEmail proEmail) {
		proEmail.setEmail(proEmail.getEmail().trim());
		if(proEmailRepository.findByEmail(proEmail.getEmail())!=null) {
			return false;
		}else {
			proEmailRepository.save(proEmail);
			return true;
		}
	}
	
	//Crud Read and display all the emails from a User
	public List<ProEmail> readByUserId(int userId) {
		return proEmailRepository.findByUserId(userId);
	}
	
	//fin by id and display the selected email 
	public Optional<ProEmail> findById(int emailId) {
		return proEmailRepository.findById(emailId);
	}
	
	
	//Crud Delete the selected pro Email
	public boolean deleteEmail(int emailId) {
		proEmailRepository.deleteById(emailId);
		return proEmailRepository.findById(emailId).isEmpty() ? true :  false ;
	}
	
	//Crud Delete the selected pro Email
		public ProEmail convertDtoToEntity(ProEmailDto proEmailDto) {
			ProEmail proEmail=new ProEmail();
			User user = new User();
			user.setId(proEmailDto.getUserId());
			proEmail.setEmail(proEmailDto.getProEmail());
			proEmail.setUser(user);
			return proEmail;
		}
}

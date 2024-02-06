package com.example.activitea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.ProEmailDto;
import com.example.activitea.entity.ProEmail;
import com.example.activitea.entity.User;
import com.example.activitea.entity.ValidationResult;
import com.example.activitea.repository.ProEmailRepository;

@Service
public class ProEmailService {
	@Autowired
	public ProEmailRepository proEmailRepository;

	//Crud create a new professionnal email which will figure on the the coverLetter
	public ValidationResult create(ProEmailDto proEmailDto){
		
		if(proEmailRepository.findByEmail(proEmailDto.getProEmail().trim())!=null) {
			return new ValidationResult(false, "votre email "+proEmailDto.getProEmail()+" existe déjà");
		}else {
			ProEmail proEmail = convertDtoToEntity(proEmailDto);
			if (proEmailRepository.save(isActive(proEmail)) != null) {
				return new ValidationResult(true, "votre email "+proEmailDto.getProEmail()+" a été enregistré");
			} else {
				return new ValidationResult(false, "votre email n'a pas pu être enregistré");
			}
			
			
		}
	}
	
	//Crud Read and display all the emails from a User
	public List<ProEmailDto> readByUserId(int userId) {
		return proEmailRepository.findByUserId(userId).stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	
	//find by id and display the selected email 
	public ProEmailDto findById(int emailId) {
		return convertEntityToDto(proEmailRepository.findById(emailId).get()) ;
	}
	
	//crud  update the proemail
	public boolean updateProEmail(int emailId, ProEmailDto proEmailDto) {
		 Optional<ProEmail> optionalProEmail = proEmailRepository.findById(emailId);  
		    if (optionalProEmail.isPresent()) {
		        ProEmail proEmail = optionalProEmail.get();
		        proEmail.setEmail(proEmailDto.getProEmail());
		        proEmailRepository.save(proEmail); // Save modifications
		        return true;
		    } else {
		        return false;
		    }
	}

	//Crud Delete the selected pro Email
	public boolean deleteEmail(int emailId) {
		proEmailRepository.deleteById(emailId);
		return proEmailRepository.findById(emailId).isEmpty() ? true :  false ;
	}
	
	//method to set Active attribute to true 
	public ProEmail isActive(ProEmail proEmail) {
		List<ProEmail> emailList= proEmailRepository.findByUserId(proEmail.getUser().getId());
		for (ProEmail email : emailList) {
			email.setActive(false);
			proEmailRepository.save(email);
		}
		proEmail.setActive(true);
		return proEmail;
	}
	
	//Convert DTO to Entity
	public ProEmail convertDtoToEntity(ProEmailDto proEmailDto) {
		ProEmail proEmail=new ProEmail();
		User user = new User();
		user.setId(proEmailDto.getUserId());
		proEmail.setEmail(proEmailDto.getProEmail());
		proEmail.setUser(user);
		return proEmail;
	}
	
	//Convert entity to Dto
	public ProEmailDto convertEntityToDto(ProEmail proEmail) {
		ProEmailDto proEmailDto=new ProEmailDto();
		proEmailDto.setId(proEmail.getId());
		proEmailDto.setProEmail(proEmail.getEmail());
		proEmailDto.setUserId(proEmail.getUser().getId());
		proEmailDto.setFirstName(proEmail.getUser().getFirstname());
		proEmailDto.setName(proEmail.getUser().getName());
		proEmailDto.setEmail(proEmail.getUser().getEmail());
		return proEmailDto;
	}
}

package com.example.activitea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.ProPhoneDto;
import com.example.activitea.entity.ProPhone;
import com.example.activitea.entity.User;
import com.example.activitea.entity.ValidationResult;
import com.example.activitea.repository.ProPhoneRepository;

@Service
public class ProPhoneService {

	@Autowired
	private ProPhoneRepository proPhoneRepository;
	
		//Crud create a new Pro Phone number which will figure on the the coverLetter
		public ValidationResult create(ProPhoneDto proPhoneDto) {
			ProPhone proPhone =convertDtoToEntity(proPhoneDto);
			if (proPhoneRepository.findByPhone(proPhone.getPhone())!= null) {
				return new ValidationResult(false, "Le numéro "+proPhone.getPhone()+" existe déja");
				
			} else {
				if(proPhoneRepository.save(isActive(proPhone))!=null) {
					return new ValidationResult(true, "Le numéro "+proPhone.getPhone()+" a bien été enrgistré");
				}else {
					return new ValidationResult(false, "Le numéro n'a pas pu être enregistré, veuillez réessayer");
				}
			}
		}
		
		//Crud Read and display all pro phone numbers from a User
		public List<ProPhoneDto> readByUserId(int userId) {
			return proPhoneRepository.findByUserId(userId).stream().map(this::convertEntityToDto).collect(Collectors.toList());
		}
		
		//find by id and display the selected phone number 
		public ProPhoneDto findById(int proPhoneId) {
			return convertEntityToDto(proPhoneRepository.findById(proPhoneId).get());
		}
		
		//Crud Delete the selected pro phone number
		public boolean deleteProPhone(int proPhoneId) {
			proPhoneRepository.deleteById(proPhoneId);
			return proPhoneRepository.findById(proPhoneId).isEmpty() ? true :  false ;
		}
		
		//Crud update the selected pro phone number
		public boolean updateProPhone(int phoneId, ProPhoneDto proPhonceDto) {
			 Optional<ProPhone> optionalProPhone = proPhoneRepository.findById(phoneId);
			    
			    if (optionalProPhone.isPresent()) {
			        ProPhone proPhone = optionalProPhone.get();
			        proPhone.setPhone(proPhonceDto.getPhone());
			        proPhoneRepository.save(proPhone); // Save modifications
			        return true;
			    } else {
			        return false;
			    }
		}
		
		//method to set Active attribute to true 
		public ProPhone isActive(ProPhone proPhone) {
			
			List<ProPhone> phoneList= proPhoneRepository.findByUserId(proPhone.getUser().getId());
			for (ProPhone phone : phoneList) {
				phone.setActive(false);
				proPhoneRepository.save(phone);
			}
			proPhone.setActive(true);
			return proPhone;
		}
		
		//Method to convert Dto to entity
		public ProPhone convertDtoToEntity(ProPhoneDto proPhoneDto) {
		ProPhone proPhone=new ProPhone();
		User user=new User();
		user.setId(proPhoneDto.getUserId());
		proPhone.setPhone(proPhoneDto.getPhone().trim());
		proPhone.setActive(true);
		proPhone.setUser(user);
		return proPhone;
		}
		
		//Method to convert entity to Dto
		public ProPhoneDto convertEntityToDto(ProPhone proPhone) {
			ProPhoneDto proPhoneDto=new ProPhoneDto();
			proPhoneDto.setId(proPhone.getId());
			proPhoneDto.setPhone(proPhone.getPhone());
			proPhoneDto.setUserId(proPhone.getUser().getId());
			proPhoneDto.setFirstName(proPhone.getUser().getFirstname());
			proPhoneDto.setName(proPhone.getUser().getName());
			proPhoneDto.setEmail(proPhone.getUser().getEmail());
			return proPhoneDto;
		}
}

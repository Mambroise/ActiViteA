package com.example.activitea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.ProExpDto;
import com.example.activitea.entity.ProExp;
import com.example.activitea.entity.User;
import com.example.activitea.repository.ProExpRepository;

@Service
public class ProExpService {
	
	@Autowired
	public ProExpRepository proExpRepository;

	//Crud create a new professionnal experience which will figure on the the coverLetter
	public boolean create(ProExpDto proExpDto) {
		proExpDto.setCompany(proExpDto.getCompany().trim().toLowerCase());
		proExpDto.setTitle(proExpDto.getTitle().trim().toLowerCase());
		return proExpRepository.save(convertDtoToEntity(proExpDto))!=null ? true : false;
		
	}
	
	//Crud Read and display all user pro experiences
	public List<ProExpDto> readByUserId(int userId) {
		return proExpRepository.findByUserId(userId).stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	
	//fin by id and display the selected experience 
	public ProExpDto findById(int proExpId) {
		return convertEntityToDto(proExpRepository.findById(proExpId).get()) ;
	}
	
	
	//Crud Delete the selected pro experience
	public boolean deleteProExp(int proExpId) {
		proExpRepository.deleteById(proExpId);
		return proExpRepository.findById(proExpId).isEmpty() ? true :  false ;
	}

	public boolean updateProExp(int proExpId, ProExpDto proExpDto) {
	 Optional<ProExp> optionalProExp = proExpRepository.findById(proExpId);
	    
	    if (optionalProExp.isPresent()) {
	        ProExp proExp = optionalProExp.get();
	        proExp.setCompany(proExpDto.getCompany());
	        proExp.setTitle(proExpDto.getTitle());
	        proExp.setStartDate(proExpDto.getStartDate());
	        proExp.setEndDate(proExpDto.getEndDate());
	        proExpRepository.save(proExp); // Save modifications
	        return true;
	    } else {
	        return false;
	    }
	}
	
	//Method to convert Dto to entity
	public ProExp convertDtoToEntity(ProExpDto proExpDto) {
	ProExp proExp=new ProExp();
	User user=new User();
	user.setId(proExpDto.getUserId());
	proExp.setCompany(proExpDto.getCompany().trim());
	proExp.setTitle(proExpDto.getTitle().trim());
	proExp.setStartDate(proExpDto.getStartDate());
	proExp.setEndDate(proExpDto.getEndDate());
	proExp.setUser(user);
	return proExp;
	}
	
	//Method to convert entity to Dto
	public ProExpDto convertEntityToDto(ProExp proExp) {
		ProExpDto proExpDto=new ProExpDto();
		proExpDto.setId(proExp.getId());
		proExpDto.setCompany(proExp.getCompany());
		proExpDto.setTitle(proExp.getTitle());
		proExpDto.setStartDate(proExp.getStartDate());
		proExpDto.setEndDate(proExp.getEndDate());
		proExpDto.setUserId(proExp.getUser().getId());
		proExpDto.setFirstName(proExp.getUser().getFirstname());
		proExpDto.setName(proExp.getUser().getName());
		proExpDto.setEmail(proExp.getUser().getEmail());
		return proExpDto;
	}
}

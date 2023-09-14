package com.example.activitea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.LifeExpDto;
import com.example.activitea.entity.LifeExp;
import com.example.activitea.entity.User;
import com.example.activitea.repository.LifeExpRepository;

@Service
public class LifeExpService {

	@Autowired
	public LifeExpRepository lifeExpRepository;

	//Crud create a new professionnal experience which will figure on the the coverLetter
	public boolean create(LifeExpDto lifeExpDto) {
		return lifeExpRepository.save(convertDtoToEntity(lifeExpDto))!=null ? true : false;
		
	}
	
	//Crud Read and display all user pro experiences
	public List<LifeExpDto> readByUserId(int userId) {
		return lifeExpRepository.findByUserId(userId).stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	
	//fin by id and display the selected experience 
	public LifeExpDto findById(int lifeExpId) {
		return convertEntityToDto(lifeExpRepository.findById(lifeExpId).get()) ;
	}
	
	//crud  update the proemail
	public boolean updateLifeExp(int lifeExpId, LifeExpDto lifeExpDto) {
		 Optional<LifeExp> optionalLifeExp = lifeExpRepository.findById(lifeExpId);
		    
		    if (optionalLifeExp.isPresent()) {
		        LifeExp lifeExp = optionalLifeExp.get();
		        lifeExp.setContent(lifeExpDto.getContent());
		        lifeExpRepository.save(lifeExp); // Save modifications
		        return true;
		    } else {
		        return false;
		    }
	}
	
	//Crud Delete the selected pro experience
	public boolean deleteLifeExp(int lifeExpId) {
		lifeExpRepository.deleteById(lifeExpId);
		return lifeExpRepository.findById(lifeExpId).isEmpty() ? true :  false ;
	}
	
	//Method to convert Dto to entity
	public LifeExp convertDtoToEntity(LifeExpDto lifeExpDto) {
	LifeExp lifeExp=new LifeExp();
	User user=new User();
	user.setId(lifeExpDto.getUserId());
	lifeExp.setContent(lifeExpDto.getContent().trim().toLowerCase());
	lifeExp.setUser(user);
	return lifeExp;
	}
	
	//Method to convert entity to Dto
	public LifeExpDto convertEntityToDto(LifeExp lifeExp) {
		LifeExpDto lifeExpDto=new LifeExpDto();
		lifeExpDto.setId(lifeExp.getId());
		lifeExpDto.setContent(lifeExp.getContent());
		lifeExpDto.setUserId(lifeExp.getUser().getId());
		lifeExpDto.setFirstName(lifeExp.getUser().getFirstname());
		lifeExpDto.setName(lifeExp.getUser().getName());
		lifeExpDto.setEmail(lifeExp.getUser().getEmail());
		return lifeExpDto;
	}
}

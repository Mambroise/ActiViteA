package com.example.activitea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.LanguageDto;
import com.example.activitea.entity.Language;
import com.example.activitea.entity.User;
import com.example.activitea.repository.LanguageRepository;

@Service
public class LanguageService {

	@Autowired
	public LanguageRepository languageRepository;

	//Crud create a new professionnal experience which will figure on the the coverLetter
	public boolean create(LanguageDto languageDto) {
		return languageRepository.save(convertDtoToEntity(languageDto))!=null ? true : false;
		
	}
	
	//Crud Read and display all user pro experiences
	public List<LanguageDto> readByUserId(int userId) {
		return languageRepository.findByUserId(userId).stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	
	//fin by id and display the selected experience 
	public LanguageDto findById(int languageId) {
		return convertEntityToDto(languageRepository.findById(languageId).get()) ;
	}
	
	
	//Crud Delete the selected pro experience
	public boolean deleteLanguage(int languageId) {
		languageRepository.deleteById(languageId);
		return languageRepository.findById(languageId).isEmpty() ? true :  false ;
	}
	
	//crud  update the experience
	public boolean updateCursus(int languageId, LanguageDto languageDto) {
		 Optional<Language> optionalLanguage = languageRepository.findById(languageId);
		    
		    if (optionalLanguage.isPresent()) {
		        Language language = optionalLanguage.get();
		        language.setLanguage(null);
		        language.setStars(languageDto.getStars());
		        languageRepository.save(language); // Save modifications
		        return true;
		    } else {
		        return false;
		    }
	}
	// Method to convert languageDto into language
	public Language convertDtoToEntity(LanguageDto languageDto) {
		Language language = new Language();
		User user = new User();
		user.setId(languageDto.getUserId());
		language.setLanguage(languageDto.getLanguage().trim().toLowerCase());
		language.setStars(languageDto.getStars());
		language.setUser(user);
		return language;
	}
	
	// Method to convert language into languageDTO
	public LanguageDto convertEntityToDto(Language language) {
		LanguageDto languageDto = new LanguageDto();
		languageDto.setId(language.getId());
		languageDto.setLanguage(language.getLanguage());
		languageDto.setStars(language.getStars());
		languageDto.setUserId(language.getUser().getId());
		languageDto.setFirstName(language.getUser().getFirstname());
		languageDto.setName(language.getUser().getName());
		languageDto.setEmail(language.getUser().getEmail());
		return languageDto;
	}
}

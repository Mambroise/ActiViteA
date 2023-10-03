package com.example.activitea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.CoverLetterDto;
import com.example.activitea.entity.CoverLetter;
import com.example.activitea.entity.User;
import com.example.activitea.repository.CoverLetterRepository;

@Service
public class CoverLetterService {
	
	@Autowired
	private CoverLetterRepository coverLetterRepository;
	
	//create 
	public boolean create(CoverLetterDto coverLetterDto) {
		return coverLetterRepository.save(convertDtoToEntity(coverLetterDto))!=null ? true : false; 
	};
	
	public List<CoverLetterDto> readByUserId(int userId){
		return coverLetterRepository.findByUserId(userId).stream().map(this::convertEntityToDto).collect(Collectors.toList());
	};
	
	public CoverLetter getLastCoverLetter(int userId) {
	        return coverLetterRepository.findTopByUserIdOrderByIdDesc(userId);
	};
	
	//fin by id and display the selected coverletter 
	public CoverLetterDto findById(int coverletterId) {
		return convertEntityToDto(coverLetterRepository.findById(coverletterId).get()) ;
	};
	
	//delete the coverletter slected by id
	public boolean deleteCoverLetter(int userId) {
		coverLetterRepository.deleteById(userId);
		return coverLetterRepository.findById(userId).isEmpty() ? true :  false ;
	};

	//Crud Update the coverletter
	public boolean updateCoverLetter(int coverLetterId, CoverLetterDto coverLetterDto) {
		 Optional<CoverLetter> optionalCoverLetter = coverLetterRepository.findById(coverLetterId);
		    
		    if (optionalCoverLetter.isPresent()) {
		        CoverLetter coverLetter = optionalCoverLetter.get();
		        coverLetter.setTitle(coverLetterDto.getTitle());
		        coverLetter.setWork_ad(coverLetterDto.getWork_ad());
		        coverLetter.setLetter(coverLetterDto.getLetter());
		        coverLetterRepository.save(coverLetter); // Save modifications
		        return true;
		    } else {
		        return false;
		    }
		}
		
	//Method to convert Dto to entity
	public CoverLetter convertDtoToEntity(CoverLetterDto coverLetterDto) {
	CoverLetter coverLetter=new CoverLetter();
	User user=new User();
	user.setId(coverLetterDto.getUserId());
	coverLetter.setTitle(coverLetterDto.getTitle());
	coverLetter.setWork_ad(coverLetterDto.getWork_ad());
	coverLetter.setLetter(coverLetterDto.getLetter());
	coverLetter.setUrl(coverLetterDto.getUrl());
	coverLetter.setUser(user);
	return coverLetter;
	}
	
	//Method to convert entity to Dto
	public CoverLetterDto convertEntityToDto(CoverLetter coverLetter) {
		CoverLetterDto coverLetterDto=new CoverLetterDto();
		coverLetterDto.setId(coverLetter.getId());
		coverLetterDto.setTitle(coverLetter.getTitle());
		coverLetterDto.setWork_ad(coverLetter.getWork_ad());
		coverLetterDto.setLetter(coverLetter.getLetter());
		coverLetterDto.setUrl(coverLetter.getUrl());
		coverLetterDto.setUserId(coverLetter.getUser().getId());
		coverLetterDto.setFirstName(coverLetter.getUser().getFirstname());
		coverLetterDto.setName(coverLetter.getUser().getName());
		coverLetterDto.setEmail(coverLetter.getUser().getEmail());
		return coverLetterDto;
	}
}

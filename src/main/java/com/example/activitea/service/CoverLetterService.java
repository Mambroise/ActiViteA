package com.example.activitea.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.entity.CoverLetter;
import com.example.activitea.repository.CoverLetterRepository;

@Service
public class CoverLetterService {
	
	@Autowired
	private CoverLetterRepository coverLetterRepository;
	
	//create 
	public boolean create(CoverLetter coverLetter) {
		return coverLetterRepository.save(coverLetter)!=null ? true : false; 
	};
	
	public List<CoverLetter> readByUserId(int userId){
		return coverLetterRepository.findByUserId(userId);
	};
	
	//fin by id and display the selected email 
	public Optional<CoverLetter> findById(int addressId) {
		return coverLetterRepository.findById(addressId);
	}
	
	public boolean deleteCoverLetter(int userId) {
		coverLetterRepository.deleteById(userId);
		return coverLetterRepository.findById(userId).isEmpty() ? true :  false ;
	};

}

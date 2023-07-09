package com.example.activitea.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.entity.ProExp;
import com.example.activitea.repository.ProExpRepository;

@Service
public class ProExpService {
	
	@Autowired
	public ProExpRepository proExpRepository;

	//Crud create a new professionnal experience which will figure on the the coverLetter
	public boolean create(ProExp proExp) {
		proExp.setCompany(proExp.getCompany().trim().toLowerCase());
		proExp.setTitle(proExp.getTitle().trim().toLowerCase());
		return proExpRepository.save(proExp)!=null ? true : false;
		
	}
	
	//Crud Read and display all user pro experiences
	public List<ProExp> readByUserId(int userId) {
		return proExpRepository.findByUserId(userId);
	}
	
	//fin by id and display the selected experience 
	public Optional<ProExp> findById(int proExpId) {
		return proExpRepository.findById(proExpId);
	}
	
	
	//Crud Delete the selected pro experience
	public boolean deleteProExp(int proExpId) {
		proExpRepository.deleteById(proExpId);
		return proExpRepository.findById(proExpId).isEmpty() ? true :  false ;
	}

}

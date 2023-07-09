package com.example.activitea.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.entity.LifeExp;
import com.example.activitea.repository.LifeExpRepository;

@Service
public class LifeExpService {

	@Autowired
	public LifeExpRepository lifeExpRepository;

	//Crud create a new professionnal experience which will figure on the the coverLetter
	public boolean create(LifeExp lifeExp) {
		lifeExp.setContent(lifeExp.getContent().trim().toLowerCase());
		return lifeExpRepository.save(lifeExp)!=null ? true : false;
		
	}
	
	//Crud Read and display all user pro experiences
	public List<LifeExp> readByUserId(int userId) {
		return lifeExpRepository.findByUserId(userId);
	}
	
	//fin by id and display the selected experience 
	public Optional<LifeExp> findById(int lifeExpId) {
		return lifeExpRepository.findById(lifeExpId);
	}
	
	
	//Crud Delete the selected pro experience
	public boolean deleteLifeExp(int lifeExpId) {
		lifeExpRepository.deleteById(lifeExpId);
		return lifeExpRepository.findById(lifeExpId).isEmpty() ? true :  false ;
	}
}

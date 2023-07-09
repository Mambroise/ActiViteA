package com.example.activitea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.CursusDto;
import com.example.activitea.entity.Cursus;
import com.example.activitea.entity.User;
import com.example.activitea.repository.CursusRepository;

@Service
public class CursusService {

	@Autowired
	public CursusRepository cursusRepository;

	//Crud create a new professionnal experience which will figure on the the coverLetter
	public boolean create(CursusDto cursusDto) {
		return cursusRepository.save(convertDtoToEntity(cursusDto))!=null ? true : false;
		
	}
	
	//Crud Read and display all user pro experiences
	public List<CursusDto> readByUserId(int userId) {
		return cursusRepository.findByUserId(userId).stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	
	//find by id and display the selected experience 
	public CursusDto findById(int cursusId) {	
		return convertEntityToDto(cursusRepository.findById(cursusId).get()) ;
	}
	
	
	//Crud Delete the selected pro experience
	public boolean deleteCursus(int cursusId) {
		cursusRepository.deleteById(cursusId);
		return cursusRepository.findById(cursusId).isEmpty() ? true :  false ;
	}
	
	//crud  update the experience
	public boolean updateCursus(int cursusId, CursusDto cursusDto) {
		 Optional<Cursus> optionalCursus = cursusRepository.findById(cursusId);
		    
		    if (optionalCursus.isPresent()) {
		        Cursus cursus = optionalCursus.get();
		        cursus.setDiploma(cursusDto.getDiploma());
		        cursus.setSchool(cursusDto.getSchool());
		        cursusRepository.save(cursus); // Save modifications
		        return true;
		    } else {
		        return false;
		    }
	}
	
	// Method to convert cursus into cursusDTO
	private CursusDto convertEntityToDto(Cursus cursus) {
		CursusDto cursusDto = new CursusDto();
		cursusDto.setId(cursus.getId());
		cursusDto.setSchool(cursus.getSchool());
		cursusDto.setDiploma(cursus.getDiploma());
		cursusDto.setDate(cursus.getDate());
		cursusDto.setUserId(cursus.getUser().getId());
		cursusDto.setFirstName(cursus.getUser().getFirstname());
		cursusDto.setName(cursus.getUser().getName());
		cursusDto.setEmail(cursus.getUser().getEmail());
		return cursusDto;
		
	}
	// Method to convert cursusDto into cursus
	private Cursus convertDtoToEntity(CursusDto cursusDto) {
		Cursus cursus = new Cursus();
		User user = new User();
		cursus.setSchool(cursusDto.getSchool().trim().toLowerCase());
		cursus.setDiploma(cursusDto.getDiploma().trim().toLowerCase());
		cursus.setDate(cursusDto.getDate());
		user.setId(cursusDto.getUserId());
		cursus.setUser(user);
		return cursus;
		
	}
}

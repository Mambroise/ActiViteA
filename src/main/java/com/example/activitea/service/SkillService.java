package com.example.activitea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.SkillDto;
import com.example.activitea.entity.Skill;
import com.example.activitea.entity.User;
import com.example.activitea.repository.SkillRepository;

@Service
public class SkillService {

	@Autowired
	public SkillRepository skillRepository;

	//Crud create a new professionnal skill which will figure on the the coverLetter
	public boolean create(SkillDto skillDto) {
		return skillRepository.save(convertDtotoEntity(skillDto))!=null ? true : false;
		
	}
	
	//Crud Read and display all user skills
	public List<SkillDto> readByUserId(int userId) {
		return skillRepository.findByUserId(userId).stream().map(this::convertEntitytoDto).collect(Collectors.toList());
	}
	
	//find by id and display the selected skill 
	public SkillDto findById(int skillId) {
		return convertEntitytoDto(skillRepository.findById(skillId).get());
	}
	
	public boolean updateSkill(int skillId, SkillDto skillDto) {
		Optional<Skill> optionalSkill=skillRepository.findById(skillId);
		
		if (optionalSkill.isPresent()) {
			Skill skill = new Skill();
			skill = optionalSkill.get();
			skill.setSkill(skillDto.getSkill());
			skillRepository.save(skill);
			return true;
		} else {
			return false;
		}
	}
	
	//Crud Delete the selected pro skill
	public boolean deleteSkill(int skillId) {
		skillRepository.deleteById(skillId);
		return skillRepository.findById(skillId).isEmpty() ? true :  false ;
	}
	
	// Method to convert skillDto into skill
	public Skill convertDtotoEntity(SkillDto skillDto) {
		Skill skill=new Skill();
		User user = new User();
		user.setId(skillDto.getUserId());
		skill.setSkill(skillDto.getSkill().trim().toLowerCase());
		skill.setUser(user);
		return skill;
	}
	
	// Method to convert skill into skillDTO
	public SkillDto convertEntitytoDto(Skill skill) {
		SkillDto skillDto =new SkillDto();
		skillDto.setId(skill.getId());
		skillDto.setSkill(skill.getSkill());
		skillDto.setUserId(skill.getUser().getId());
		skillDto.setFirstName(skill.getUser().getFirstname());
		skillDto.setName(skill.getUser().getName());
		skillDto.setEmail(skill.getUser().getEmail());
		return skillDto;
		
	}
}

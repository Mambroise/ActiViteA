package com.example.activitea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitea.Dto.SkillDto;
import com.example.activitea.service.SkillService;

@RestController
@CrossOrigin("http://localhost:3000")
public class SkillController {

	@Autowired
	public SkillService skillService;

	
	//Crud Create a new skill
	@PostMapping("/skill")
	public ResponseEntity<String> create(@RequestBody SkillDto skillDto){
		if (skillService.create(skillDto)) {
			return new ResponseEntity<String>("La compétence "+skillDto.getSkill()+" a bien été enregistrée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("La compétence "+skillDto.getSkill()+" n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Crud Read the user skill to select one 
	@GetMapping("/skill/{id}")
	public List<SkillDto> readSkill(@PathVariable("id") int userId){
		return  skillService.readByUserId(userId);
	}
	
	// Find a skill by id before update it or other possible action
	@GetMapping("/updateskill/{id}")
	public SkillDto findEmailById(@PathVariable("id") int skillId){
		return  skillService.findById(skillId);
	}
	
	//Crud update the user skill
	@PutMapping("/skill/{id}")
	public ResponseEntity<String> updateSkill(@PathVariable("id") int skillId, @RequestBody SkillDto skillDto){
			if (skillService.updateSkill(skillId, skillDto)) {
				return new ResponseEntity<>("La compétence a bien été mis à jour", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("La compétence n'a pas pu être mis à jour", HttpStatus.ACCEPTED);
			}
		}
	
	//Crud Delete the selected skill from the user account
	@DeleteMapping("/skill/{id}")
	public ResponseEntity<String> deleteSkill(@PathVariable("id") int skillId){
		if (skillService.deleteSkill(skillId)) {
			return new ResponseEntity<String>("La compétence a bien été supprimée",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("La compétence n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
		}
	}
}

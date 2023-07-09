package com.example.activitea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.activitea.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer>{

	List<Skill> findByUserId(int userId);

}

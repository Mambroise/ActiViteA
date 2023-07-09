package com.example.activitea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.activitea.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer>{

	List<Language> findByUserId(int userId);
}

package com.example.activitea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.activitea.entity.ProPhone;

public interface ProPhoneRepository extends JpaRepository<ProPhone, Integer>{

	List<ProPhone> findByUserId(int userId);
}

package com.example.activitea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.activitea.entity.ProExp;

public interface ProExpRepository extends JpaRepository<ProExp, Integer>{

	List<ProExp> findByUserId(int userId);
}

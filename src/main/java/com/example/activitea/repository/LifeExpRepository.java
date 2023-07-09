package com.example.activitea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.activitea.entity.LifeExp;

public interface LifeExpRepository extends JpaRepository<LifeExp, Integer>{

	List<LifeExp> findByUserId(int userId);
}

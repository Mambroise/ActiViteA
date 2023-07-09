package com.example.activitea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.activitea.entity.Cursus;

public interface CursusRepository extends JpaRepository<Cursus, Integer>{

	List<Cursus> findByUserId(int userId);
}

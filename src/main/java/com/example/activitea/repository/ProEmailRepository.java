package com.example.activitea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.activitea.entity.ProEmail;

public interface ProEmailRepository extends JpaRepository<ProEmail, Integer>{

	ProEmail findByEmail (String email);

//	@Query(value="SELECT * FROM pro_email WHERE user_id =?1", nativeQuery=true)
//	List<ProEmail> findEmailByUserId(int userId);
	List<ProEmail> findByUserId(int userId);
	
}

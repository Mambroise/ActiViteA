package com.example.activitea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.activitea.entity.ProPhone;

public interface ProPhoneRepository extends JpaRepository<ProPhone, Integer>{

	List<ProPhone> findByUserId(int userId);
	List<ProPhone> findByPhone(String phone);
	Optional<ProPhone> findByUserIdAndActiveIsTrue(int userId);
}

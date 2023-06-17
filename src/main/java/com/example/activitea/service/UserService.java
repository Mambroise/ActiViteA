package com.example.activitea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.activitea.entity.User;
import com.example.activitea.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepo;
	
	//CRUD Create a user
	public boolean createUser(User user) {
		user.setName(user.getName().trim().toLowerCase());
		user.setFirstname(user.getFirstname().trim().toLowerCase());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword().trim()));
		if (userRepo.save(user)!=null) {
			return true;
		}
		return false;
	}
}

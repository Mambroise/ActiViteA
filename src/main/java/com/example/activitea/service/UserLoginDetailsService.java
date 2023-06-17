package com.example.activitea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.activitea.entity.User;
import com.example.activitea.entity.UserLogin;
import com.example.activitea.repository.UserRepository;



public class UserLoginDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByEmail(username);
		if (user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new UserLogin(user);
	}

}

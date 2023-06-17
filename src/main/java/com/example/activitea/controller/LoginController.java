package com.example.activitea.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitea.config.jwt.JwtUtils;
import com.example.activitea.entity.JwtResponse;
import com.example.activitea.entity.User;
import com.example.activitea.entity.UserLogin;

import jakarta.validation.Valid;

@RestController
public class LoginController {
	

	@Autowired
	  AuthenticationManager authenticationManager;
	  
	  @Autowired
	  JwtUtils jwtUtils;
	  
	@PostMapping("/login")
	public ResponseEntity<?> authentificateUser(@Valid @RequestBody User loginRequest){
		System.err.println("connexion ok");
		Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		    SecurityContextHolder.getContext().setAuthentication(authentication);
		    String jwt = jwtUtils.generateJwtToken(authentication);
		    
		    UserLogin userDetails =  (UserLogin) authentication.getPrincipal();    
		    List<String> roles = userDetails.getAuthorities().stream()
		        .map(item -> item.getAuthority())
		        .collect(Collectors.toList());

		    return ResponseEntity.ok(new JwtResponse(jwt, 
		                         userDetails.getUser().getId(),userDetails.getFullName(), 
		                         userDetails.getUser().getEmail(), 
		                         roles));
		}

}

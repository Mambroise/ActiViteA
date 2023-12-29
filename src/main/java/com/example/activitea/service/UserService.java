package com.example.activitea.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.PasswordDto;
import com.example.activitea.Dto.UserDto;
import com.example.activitea.entity.PasswordChangeResult;
import com.example.activitea.entity.Role;
import com.example.activitea.entity.User;
import com.example.activitea.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepo;
	
	//CRUD Create a user
	public boolean createUser(UserDto userDto) {
		return userRepo.save(convertDtoToEntity(userDto))!=null ? true : false;
	}
	
	//CRUD Read user in admin pages
	public List<User> readUser () {
		System.out.println(userRepo.findAll());
		return userRepo.findAll();
	}
	
	//fin by id and display the selected coverletter 
	public UserDto findById(int userId) {
		return convertEntityToDto(userRepo.findById(userId).get()) ;
	}
	
	public boolean deleteCoverLetter(int userId) {
		userRepo.deleteById(userId);
		return userRepo.findById(userId).isEmpty() ? true :  false ;
	};

	//Crud Update the user informations except password
	public boolean updateUser(int userId, UserDto userDto) {
		 Optional<User> optionalUser = userRepo.findById(userId);
		    
		    if (optionalUser.isPresent()) {
		        User user = optionalUser.get();
		        user.setFirstname(userDto.getFirstName());;
		        user.setName(userDto.getName());;
		        user.setEmail(userDto.getEmail());;
		        userRepo.save(user); // Save modifications
		        return true;
		    } else {
		        return false;
		    }
	}
	//Crud Update the user password
	public PasswordChangeResult changePassword(int userId, PasswordDto passwordDto) {
		Optional<User> optionalUser = userRepo.findById(userId);
		
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (!new BCryptPasswordEncoder().matches(passwordDto.getOldPassword(),user.getPassword()) ) {
				return new PasswordChangeResult(false,"L'ancien mot de passe ne correspond pas" );
			} else {
				user.setPassword(new BCryptPasswordEncoder().encode(passwordDto.getNewPassword().trim()));;
				userRepo.save(user); // Save modifications
				return new PasswordChangeResult(true, "Votre mot de passe a bien été mis à jour");
			}
		} else {
			return new PasswordChangeResult(false,"Utilisateur non trouvé");
		}
	}
		
	//Method to convert Dto to entity
	public User convertDtoToEntity(UserDto userDto) {
		User user=new User();
	Set<Role> roles=new HashSet<>();
	for (Role role : userDto.getRole()) {
		roles.add(role);
	}
	user.setGender(userDto.getGender());
	user.setName(userDto.getName().trim().toLowerCase());
	user.setFirstname(userDto.getFirstName().trim().toLowerCase());
	user.setEmail(userDto.getEmail().trim());
	user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword().trim()));
	user.setRoles(roles);
	return user;
	}
	
	//Method to convert entity to Dto
	public UserDto convertEntityToDto(User user) {
	UserDto userDto=new UserDto();
	userDto.setId(user.getId());
	userDto.setGender(user.getGender());
	userDto.setName(user.getName());
	userDto.setFirstName(user.getFirstname());
	userDto.setEmail(user.getEmail());
	userDto.setPassword(user.getPassword());
	return userDto;
	}
}

package com.example.activitea.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.activitea.Dto.ContactDto;
import com.example.activitea.Dto.PasswordDto;
import com.example.activitea.Dto.UserDto;
import com.example.activitea.entity.Address;
import com.example.activitea.entity.ProEmail;
import com.example.activitea.entity.ProPhone;
import com.example.activitea.entity.Role;
import com.example.activitea.entity.User;
import com.example.activitea.entity.ValidationResult;
import com.example.activitea.repository.AddressRepository;
import com.example.activitea.repository.CursusRepository;
import com.example.activitea.repository.LanguageRepository;
import com.example.activitea.repository.LifeExpRepository;
import com.example.activitea.repository.ProEmailRepository;
import com.example.activitea.repository.ProExpRepository;
import com.example.activitea.repository.ProPhoneRepository;
import com.example.activitea.repository.SkillRepository;
import com.example.activitea.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private SkillRepository skillRepo;
	@Autowired
	private ProPhoneRepository proPhoneRepo;
	@Autowired
	private ProExpRepository proExpRepo;
	@Autowired
	private ProEmailRepository proEmailRepo;
	@Autowired
	private LifeExpRepository lifeExpRepo;
	@Autowired
	private CursusRepository cursusRepo;
	@Autowired
	private LanguageRepository languageRepo;
	@Autowired
	private AddressRepository addressRepo;
	
	//CRUD Create a user
	public boolean createUser(UserDto userDto) {
		return userRepo.save(convertDtoToEntity(userDto))!=null ? true : false;
	}
	
	//CRUD Read user in admin pages
	public List<User> readUser () {
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
	public ValidationResult changePassword(int userId, PasswordDto passwordDto) {
		Optional<User> optionalUser = userRepo.findById(userId);
		
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (!new BCryptPasswordEncoder().matches(passwordDto.getOldPassword(),user.getPassword()) ) {
				return new ValidationResult(false,"L'ancien mot de passe ne correspond pas" );
			} else {
				user.setPassword(new BCryptPasswordEncoder().encode(passwordDto.getNewPassword().trim()));;
				userRepo.save(user); // Save modifications
				return new ValidationResult(true, "Votre mot de passe a bien été mis à jour");
			}
		} else {
			return new ValidationResult(false,"Utilisateur non trouvé");
		}
	}
	
	//Method to check the user's data presence in database
	public ValidationResult dataControl(int userId) {
		//find user and get the firstname
		Optional<User> optionalUser =userRepo.findById(userId);
		if (optionalUser.isPresent()) {
			String userFirstname = optionalUser.get().getFirstname();
			
			//looking for user data
			int totalData = skillRepo.findByUserId(userId).size() + proPhoneRepo.findByUserId(userId).size() +
					proExpRepo.findByUserId(userId).size() + proEmailRepo.findByUserId(userId).size() +
					lifeExpRepo.findByUserId(userId).size() + cursusRepo.findByUserId(userId).size() +
					languageRepo.findByUserId(userId).size() + addressRepo.findByUserId(userId).size();
			
			if (totalData == 0) {
				return new ValidationResult(false, "Hey "+ userFirstname+"! Il semble que tu n'as pas"
						+ " d'informations personnelles enregistrées. Nous te conseillons de le faire"
						+ " afin que ta lettre de motivation soit optimale");
			} else if(totalData < 10){
				return new ValidationResult(true, "Hey "+ userFirstname+"! Merci pour tes informations, "
						+ " cependant nous te conseillons d'en ajouter"
						+ " afin que ta lettre de motivation soit optimale");

			}else {
				return new ValidationResult(true, null);
			}
			
		} else {
			return new ValidationResult(false, "Oups! Vous ne semblez pas exister dans notre base de données,"
					+ " Veuillez réessayer");
		}
	}
	
	public ContactDto userContact(int userId) {
		ContactDto userContact = new ContactDto();
		//handling proPhone
		Optional<ProPhone> optionalProPhone = proPhoneRepo.findByUserIdAndActiveIsTrue(userId);
		if (optionalProPhone.isPresent()) {
			userContact.setPhone(optionalProPhone.get().getPhone()); ;
		} else {
			userContact.setPhone("à déterminer");
		}
		//handling proEmail
		Optional<ProEmail> optionalProEmail = proEmailRepo.findByUserIdAndActiveIsTrue(userId);
		if (optionalProEmail.isPresent()) {
			userContact.setEmail(optionalProEmail.get().getEmail()); ;
		} else {
			userContact.setEmail("à déterminer");
		}
		//handling pro address
		Optional<Address> optionalAddress = addressRepo.findByUserIdAndActiveIsTrue(userId);
		if (optionalAddress.isPresent()) {
			Address address = new Address();
			address.setId(optionalAddress.get().getId());
			address.setNumber(optionalAddress.get().getNumber());
			address.setStreet(optionalAddress.get().getStreet());
			address.setZipCode(optionalAddress.get().getZipCode());
			address.setCity(optionalAddress.get().getCity());
			
			userContact.setAddress(address); ;
		} else {
			userContact.setAddress(null);
		}
		
		return userContact;
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

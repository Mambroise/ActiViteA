package com.example.activitea;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.activitea.Dto.UserDto;
import com.example.activitea.service.UserService;




@SpringBootTest
public class userTest {

	@Autowired
	public UserService userService;
	@Test
	void create() {
		UserDto user=new UserDto();
		user.setName("albert");
		user.setFirstName("Fifi");
		user.setEmail("usertest@test.fr");
		user.setPassword("123");
		
		assertEquals(true,userService.createUser(user));
		
	}
	
	
}

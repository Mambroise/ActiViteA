package com.example.activitea;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.activitea.entity.User;
import com.example.activitea.service.UserService;




@SpringBootTest
public class userTest {

	@Autowired
	public UserService userService;
	@Test
	void create() {
		User user=new User();
		user.setName("albert");
		user.setFirstname("Fifi");
		user.setEmail("usertest@test.fr");
		user.setPassword("123");
		
		assertEquals(true,userService.createUser(user));
		
	}
	
	
}

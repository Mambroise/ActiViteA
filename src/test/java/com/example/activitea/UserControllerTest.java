package com.example.activitea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.activitea.Dto.PasswordDto;
import com.example.activitea.Dto.UserDto;
import com.example.activitea.controller.UserController;
import com.example.activitea.entity.User;
import com.example.activitea.service.UserService;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        UserDto userDto = new UserDto();
        userDto.setName("Simon");
        userDto.setFirstName("Poupard");

        when(userService.createUser(userDto)).thenReturn(true);

        ResponseEntity<String> response = userController.create(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Simon Poupard a bien été créé", response.getBody());
    }

    @Test
    void testCreateUser_Failure() {
        UserDto userDto = new UserDto();
        userDto.setName("Simon");
        userDto.setFirstName("Poupard");

        when(userService.createUser(userDto)).thenReturn(false);

        ResponseEntity<String> response = userController.create(userDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Simon Poupard n'a pu être créé", response.getBody());
    }

    @Test
    void testReadUsers() {
        List<User> users = Collections.singletonList(new User());

        when(userService.readUser()).thenReturn(users);

        List<User> response = userController.read();

        assertEquals(users, response);
    }

    @Test
    void testFindUserById() {
        int userId = 1;
        UserDto userDto = new UserDto();

        when(userService.findById(userId)).thenReturn(userDto);

        UserDto response = userController.findUserById(userId);

        assertEquals(userDto, response);
    }

    @Test
    void testUpdateUser_Success() {
        int userId = 1;
        UserDto userDto = new UserDto();
        userDto.setName("Simon");
        userDto.setFirstName("Poupard");

        when(userService.updateUser(userId, userDto)).thenReturn(true);

        ResponseEntity<String> response = userController.updateUser(userId, userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Simon Poupard a bien été mis à jour", response.getBody());
    }

    @Test
    void testUpdateUser_Failure() {
        int userId = 1;
        UserDto userDto = new UserDto();
        userDto.setName("Simon");
        userDto.setFirstName("Poupard");

        when(userService.updateUser(userId, userDto)).thenReturn(false);

        ResponseEntity<String> response = userController.updateUser(userId, userDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Simon Poupard n'a pu être mis à jour", response.getBody());
    }

    @Test
    void testUpdateUserPassword_Success() {
        int userId = 1;
        PasswordDto passwordDto = new PasswordDto();

        when(userService.changePassword(userId, passwordDto)).thenReturn(true);

        ResponseEntity<String> response = userController.updateUserPassword(userId, passwordDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Votre mot de passe a bien été mis à jour", response.getBody());
    }

    @Test
    void testUpdateUserPassword_Failure() {
        int userId = 1;
        PasswordDto passwordDto = new PasswordDto();

        when(userService.changePassword(userId, passwordDto)).thenReturn(false);

        ResponseEntity<String> response = userController.updateUserPassword(userId, passwordDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Votre mot de passe n'a pas pu être mis à jour", response.getBody());
    }
}


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

import com.example.activitea.Dto.ProEmailDto;
import com.example.activitea.controller.ProEmailController;
import com.example.activitea.service.ProEmailService;

class ProEmailControllerTest {

    @Mock
    private ProEmailService proEmailService;

    @InjectMocks
    private ProEmailController proEmailController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProEmail_Success() {
        ProEmailDto proEmailDto = new ProEmailDto();
        proEmailDto.setProEmail("test@example.com");

        when(proEmailService.create(proEmailDto)).thenReturn(true);

        ResponseEntity<String> response = proEmailController.create(proEmailDto);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("L'email test@example.com a bien été enregistré", response.getBody());
    }

    @Test
    void testCreateProEmail_Failure() {
        ProEmailDto proEmailDto = new ProEmailDto();
        proEmailDto.setProEmail("test@example.com");

        when(proEmailService.create(proEmailDto)).thenReturn(false);

        ResponseEntity<String> response = proEmailController.create(proEmailDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("L'email test@example.com existe déjà", response.getBody());
    }

    @Test
    void testReadProEmail() {
        int userId = 1;
        List<ProEmailDto> proEmails = Collections.singletonList(new ProEmailDto());

        when(proEmailService.readByUserId(userId)).thenReturn(proEmails);

        List<ProEmailDto> response = proEmailController.readProEmail(userId);

        assertEquals(proEmails, response);
    }

    @Test
    void testFindEmailById() {
        int emailId = 1;
        ProEmailDto proEmailDto = new ProEmailDto();

        when(proEmailService.findById(emailId)).thenReturn(proEmailDto);

        ProEmailDto response = proEmailController.findEmailById(emailId);

        assertEquals(proEmailDto, response);
    }

    @Test
    void testUpdateProEmail_Success() {
        int emailId = 1;
        ProEmailDto proEmailDto = new ProEmailDto();
        proEmailDto.setProEmail("updated@example.com");

        when(proEmailService.updateProEmail(emailId, proEmailDto)).thenReturn(true);

        ResponseEntity<String> response = proEmailController.updateLanguage(emailId, proEmailDto);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("L'email a bien été mis à jour", response.getBody());
    }

    @Test
    void testUpdateProEmail_Failure() {
        int emailId = 1;
        ProEmailDto proEmailDto = new ProEmailDto();
        proEmailDto.setProEmail("updated@example.com");

        when(proEmailService.updateProEmail(emailId, proEmailDto)).thenReturn(false);

        ResponseEntity<String> response = proEmailController.updateLanguage(emailId, proEmailDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("L'email n'a pas pu être mis à jour", response.getBody());
    }

    @Test
    void testDeleteProEmail_Success() {
        int emailId = 1;

        when(proEmailService.deleteEmail(emailId)).thenReturn(true);

        ResponseEntity<String> response = proEmailController.deleteProEmail(emailId);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("L'email a bien été supprimé", response.getBody());
    }

    @Test
    void testDeleteProEmail_Failure() {
        int emailId = 1;

        when(proEmailService.deleteEmail(emailId)).thenReturn(false);

        ResponseEntity<String> response = proEmailController.deleteProEmail(emailId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("L'email n'a pas pu être supprimé", response.getBody());
    }
}


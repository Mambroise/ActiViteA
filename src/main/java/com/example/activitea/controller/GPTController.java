package com.example.activitea.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class GPTController {



	    @Value("${gpt.apiKey}") // Load your API key from configuration
	    private String apiKey;

	   
	    @PostMapping("/generate-text")
	    public String generateText(@RequestBody String prompt) {
	        RestTemplate restTemplate = new RestTemplate();
	        String gptApiUrl = "https://api.openai.com/v1/chat/completions";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Bearer " + apiKey);
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        Map<String, Object> requestBody = new HashMap<>();
	        requestBody.put("model", "gpt-3.5-turbo"); 

	        List<Map<String, String>> messages = new ArrayList<>();
	        Map<String, String> systemMessage = new HashMap<>();
	        systemMessage.put("role", "system");
	        systemMessage.put("content", "You are a helpful assistant.");
	        messages.add(systemMessage);

	        // Add additional messages as needed
	        Map<String, String> userMessage = new HashMap<>();
	        userMessage.put("role", "user");
	        userMessage.put("content", prompt);
	        messages.add(userMessage);

	        requestBody.put("messages", messages);
	        requestBody.put("max_tokens", 2000); // here i adjust the number of tokens  as needed

	        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
	        ResponseEntity<String> responseEntity = restTemplate.exchange(gptApiUrl, HttpMethod.POST, requestEntity, String.class);

	        if (responseEntity.getStatusCode() == HttpStatus.OK) {
	            return responseEntity.getBody();
	        } else {
	            return "Une erreur s'est produite .";
	        }
	    }
}

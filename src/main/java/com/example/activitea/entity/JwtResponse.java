package com.example.activitea.entity;

import java.util.List;

public class JwtResponse {

	private String token;
	private String type ="Bearer";
	private int id;
	private String fullname;
	private String email;
	private List<String> role;
	public JwtResponse(String accessToken, int id, String fullname, String email, List<String> role) {
		
		this.token = accessToken;
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.role = role;
		
		
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
}

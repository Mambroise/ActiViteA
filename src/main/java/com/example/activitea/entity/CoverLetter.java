package com.example.activitea.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class CoverLetter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	@NotBlank(message = "Vous devez ajouter une annonce d'emploi")
	@Column(columnDefinition="TEXT")
	private String work_ad;
	@Column(columnDefinition="TEXT")
	private String letter;
	private String url;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ProEmail proEmail;
	@ManyToOne(fetch = FetchType.LAZY)
	private ProPhone proPhone;
	@ManyToOne(fetch = FetchType.LAZY)
	private Address address;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	private Set<Language> language=new HashSet<>();
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	private Set<LifeExp> lifeExp=new HashSet<>();
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	private Set<ProExp> proExp=new HashSet<>();
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	private Set<Skill> skill=new HashSet<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWork_ad() {
		return work_ad;
	}
	public void setWork_ad(String work_ad) {
		this.work_ad = work_ad;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ProEmail getProEmail() {
		return proEmail;
	}
	public void setProEmail(ProEmail proEmail) {
		this.proEmail = proEmail;
	}
	public ProPhone getProPhone() {
		return proPhone;
	}
	public void setProPhone(ProPhone proPhone) {
		this.proPhone = proPhone;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Set<Language> getLanguage() {
		return language;
	}
	public void setLanguage(Set<Language> language) {
		this.language = language;
	}
	public Set<LifeExp> getLifeExp() {
		return lifeExp;
	}
	public void setLifeExp(Set<LifeExp> lifeExp) {
		this.lifeExp = lifeExp;
	}
	public Set<ProExp> getProExp() {
		return proExp;
	}
	public void setProExp(Set<ProExp> proExp) {
		this.proExp = proExp;
	}
	public Set<Skill> getSkill() {
		return skill;
	}
	public void setSkill(Set<Skill> skill) {
		this.skill = skill;
	}
	
	
}

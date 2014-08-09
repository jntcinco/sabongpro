package com.tekusource.sabongpro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4126430180459770331L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="middlename")
	private String middlename;
	
	@Column(name="email")
	private String email;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
//	@OneToMany(mappedBy="userDetail")
//	private List<Image> images;
	
//	@OneToMany(mappedBy="userDetail")
//	private List<Video> videos;
	
//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="userTypeId", nullable=false)
//	private UserType userType;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMiddlename() {
		return middlename;
	}
	
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	
//	public List<Image> getImages() {
//		return images;
//	}
//	
//	public void setImages(List<Image> images) {
//		this.images = images;
//	}
//	
//	public List<Video> getViideos() {
//		return videos;
//	}
//	
//	public void setVideos(List<Video> videos) {
//		this.videos = videos;
//	}
	
//	public UserType getUserType() {
//		return userType;
//	}
//	
//	public void setUserType(UserType userType) {
//		this.userType = userType;
//	}
}

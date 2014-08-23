package com.tekusource.sabongpro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="users")
public class User implements Serializable {
	
	private static final long serialVersionUID = -4126430180459770331L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="status")
	private String status;
	
	@Column(name="userToken")
	private String userToken;
	
	@Transient
	private String confirmPassword;
	
	@Column(name="is_stream_allowed", columnDefinition = "TINYINT(1)")
	private boolean isStreamAllowed;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userRoleId", insertable=true, updatable=true, nullable=false)
	private UserRole userRole;
	
	@OneToOne(mappedBy="user")
	private UserProfile profile;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUserToken() {
		return userToken;
	}
	
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isStreamAllowed() {
		return isStreamAllowed;
	}

	public void setStreamAllowed(boolean isStreamAllowed) {
		this.isStreamAllowed = isStreamAllowed;
	}
	
	public UserRole getUserRole() {
		return userRole;
	}
	
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
}

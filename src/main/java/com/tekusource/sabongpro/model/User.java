package com.tekusource.sabongpro.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column(name="enabled", columnDefinition = "TINYINT(1)")
	private boolean enabled;
	
	@Column(name="userToken")
	private String userToken;
	
	@Transient
	private String confirmPassword;
	
	@Column(name="stream_allowed", columnDefinition = "TINYINT(1)")
	private boolean streamAllowed;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userRoleId", insertable=true, updatable=true, nullable=false)
	private UserRole userRole;
	
	@OneToOne(mappedBy="user")
	private UserProfile profile;
	
	@OneToMany(mappedBy="user")
	private List<BetHistory> betHistories;
	
	@Column(name="virtual_points")
	private Double virtualPoints;
	
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
		return streamAllowed;
	}

	public void setStreamAllowed(boolean streamAllowed) {
		this.streamAllowed = streamAllowed;
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
	
	public List<BetHistory> getBetHistories() {
		return betHistories;
	}
	
	public void setBetHistories(List<BetHistory> betHistories) {
		this.betHistories = betHistories;
	}

	public Double getVirtualPoints() {
		return virtualPoints;
	}

	public void setVirtualPoints(Double virtualPoints) {
		this.virtualPoints = virtualPoints;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

package com.tekusource.sabongpro.service;

import java.util.Map;

import com.tekusource.sabongpro.model.UserProfile;

public interface UserProfileService {
	
	public static final String USER_ID = "user_id";
	
	public void save(UserProfile profile);
	
	public void update(UserProfile profile);
	
	public UserProfile getUserProfileBy(Long id);
	
	public UserProfile getUserProfileByUserId(Long id);
	
	public UserProfile getUserProfileBy(Map<String, Object> values);
}

package com.tekusource.sabongpro.service;

import java.util.List;
import java.util.Map;

import com.tekusource.sabongpro.model.User;

public interface UserService {
	
	public static final String LAST_NAME					= "lastname";
	public static final String FIRST_NAME					= "firstname";
	public static final String EMAIL						= "email";
	public static final String USER_NAME					= "username";
	public static final String PASS_WORD					= "password";
	
	public void save(User user);
	
	public void update(User user);
	
	public void remove(Long id);
	
	public User getUserBy(Long id);
	
	public User getUserByUsername(String value);
	
	public User getUserBy(Map<String, Object> values);
	
	public List<User> getAllUser();
	
	public boolean isUserExist(String username, String password);
	
	public boolean isUsernameExist(String username);
}

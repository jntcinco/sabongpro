package com.tekusource.sabongpro.service;

import java.util.List;
import java.util.Map;

import com.tekusource.sabongpro.model.User;

public interface UserService {
	
	public void save(User user);
	
	public void update(User user);
	
	public void remove(Long id);
	
	public User getUserBy(Long id);
	
	public User getUserByUserName(String value);
	
	public User getUserBy(Map<String, Object> values);
	
	public User getUserBy(String email);
	
	public List<User> getAllUser();
	
	public List<User> getUsersBy(String email, String fieldName);
	
	public boolean isUserExist(String username, String password);
	
	public boolean isUserNameExist(String username);
	
	public boolean isUserTokenValid(String username, String userToken);
	
	public String createUserToken(User user);
	
	public String decryptString(String value);
	
	public String decryptPassword(String value);
	
	public String encryptString(String value);
	
	public String encryptPassword(String value);
}

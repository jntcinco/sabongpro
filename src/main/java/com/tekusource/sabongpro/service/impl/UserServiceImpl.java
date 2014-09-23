package com.tekusource.sabongpro.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.spring.security3.PBEPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.sabongpro.constants.ServiceConstants;
import com.tekusource.sabongpro.dao.UserDao;
import com.tekusource.sabongpro.model.StatusType;
import com.tekusource.sabongpro.model.StreamingConfig;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
    private PBEPasswordEncoder passwordEncoder;

    @Autowired
    private PBEStringEncryptor passwordEncryptor;
	
	public void save(User user) {
		if(user != null) {
			userDao.create(user);
		}
	}
	
	public void update(User user) {
		if(user != null) {
			User updateUser = (User) getUserBy(user.getId());
			if(updateUser != null) {
				updateUser.setBetHistories(user.getBetHistories());
				updateUser.setConfirmPassword(user.getConfirmPassword());
				updateUser.setEmail(user.getEmail());
				updateUser.setPassword(user.getPassword());
				updateUser.setProfile(user.getProfile());
				updateUser.setStatus(user.getStatus());
				updateUser.setStreamAllowed(user.isStreamAllowed());
				updateUser.setUserName(user.getUserName());
				updateUser.setUserRole(user.getUserRole());
				updateUser.setUserToken(user.getUserToken());
				updateUser.setVirtualPoints(user.getVirtualPoints());
				userDao.update(updateUser);
			}
		}
	}
	
	public void remove(Long id) {
		if(id != null) {
			userDao.remove(id);
		}
	}
	
	public User getUserBy(Long id) {
		return userDao.get(id);
	}
	
	public User getUserByUserName(String value) {
		return (User) userDao.getBy(ServiceConstants.USER_NAME, value);
	}
	
	public User getUserBy( Map<String, Object> values) {
		Map<String, Boolean> orders = new HashMap<String, Boolean>();
		orders.put(ServiceConstants.USER_NAME, true);
		return (User) userDao.getBy(values, orders);
	}
	
	public User getUserBy(String email){
		return (User) userDao.getBy(ServiceConstants.EMAIL, email);
	}
	
	public List<User> getAllUser() {
		return userDao.getAll();
	}
	
	public List<User> getUsersBy(String email, String fieldName){
		return userDao.getUsersBy(email, fieldName);
	}
	
	public boolean isUserExist(String username, String password) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put( "userName", username );
		values.put( "password", password );

        Map<String, Boolean> orders = new HashMap<String, Boolean>();
        orders.put( "userName", true );
		User user = (User) userDao.getBy(values, orders);
		
		if(user != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isUserNameExist(String username) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put( "userName", username );
		Map<String, Boolean> orders = new HashMap<String, Boolean>();
        orders.put( "userName", true );
        
		User userCheck = (User) userDao.getBy(values, orders);
		if(userCheck == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isUserTokenValid(String username, String userToken) {
		boolean isValid = false;
		User user = getUserByUserName(username);
		if(user != null) {
			String urlToken = decryptString(userToken);
			String dbToken = decryptString(user.getUserToken());
			if(urlToken.equals(dbToken)) {
//				user.setStatus(StatusType.ACTIVE.getDescription());
				user.setEnabled(true);
				update(user);
				isValid = true;
			}
		}
		return isValid;
	}
	
	public String createUserToken(User user) {
		return encryptString(new StringBuilder(user.getEmail()).append(":").append(user.getUserName()).toString());
	}
	
	public String encryptString(String value) {
		return new String(Base64.encodeBase64(value.getBytes()));
	}
	
	public String decryptString(String value) {
		return  new String(Base64.decodeBase64(value));
	}
	
	public String encryptPassword(String value) {
		return passwordEncoder.encodePassword(value, null);
	}
	
	public String decryptPassword(String value) {
		return passwordEncryptor.decrypt(value);
	}
	

}

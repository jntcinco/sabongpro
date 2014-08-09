package com.tekusource.sabongpro.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.sabongpro.dao.UserDao;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public void save(User user) {
		if(user != null) {
			userDao.create(user);
		}
	}
	
	public void update(User user) {
		if(user != null) {
			userDao.update(user);
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
	
	public User getUserByUsername(String value) {
		return (User) userDao.getBy(USER_NAME, value);
	}
	
	public User getUserBy( Map<String, Object> values) {
		Map<String, Boolean> orders = new HashMap<String, Boolean>();
		orders.put(LAST_NAME, true);
		return (User) userDao.getBy(values, orders);
	}
	
	public List<User> getAllUser() {
		return userDao.getAll();
	}
	
	public boolean isUserExist(String username, String password) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put( "username", username );
		values.put( "password", password );

        Map<String, Boolean> orders = new HashMap<String, Boolean>();
        orders.put( "username", true );
		User user = (User) userDao.getBy(values, orders);
		
		if(user != null) {
			return true;
		} else {
			return false;
		}
	}
}

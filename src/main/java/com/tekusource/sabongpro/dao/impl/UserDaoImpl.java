package com.tekusource.sabongpro.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tekusource.sabongpro.dao.UserDao;
import com.tekusource.sabongpro.model.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsersBy(String email, String fieldName){
		return entityManager.createQuery( "from " + persistentClass.getName() + " where " + fieldName + "='" + email+"'").getResultList();
	}
}

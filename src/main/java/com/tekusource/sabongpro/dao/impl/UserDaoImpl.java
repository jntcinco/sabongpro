package com.tekusource.sabongpro.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.sabongpro.dao.UserDao;
import com.tekusource.sabongpro.model.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}
}

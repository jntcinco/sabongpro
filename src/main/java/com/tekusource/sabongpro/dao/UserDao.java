package com.tekusource.sabongpro.dao;

import java.util.List;

import com.tekusource.sabongpro.model.User;

public interface UserDao extends GenericDao<User, Long> {
	List<User> getUsersBy(String email, String fieldName);
}

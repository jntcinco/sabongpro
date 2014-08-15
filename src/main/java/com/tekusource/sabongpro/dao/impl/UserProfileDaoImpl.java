package com.tekusource.sabongpro.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.sabongpro.dao.UserProfileDao;
import com.tekusource.sabongpro.model.UserProfile;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends GenericDaoImpl<UserProfile, Long> implements
		UserProfileDao {

	public UserProfileDaoImpl(){
		super(UserProfile.class);
	}
}

package com.tekusource.sabongpro.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.sabongpro.dao.UserProfileDao;
import com.tekusource.sabongpro.model.UserProfile;
import com.tekusource.sabongpro.service.UserProfileService;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileDao userProfileDao;
	
	@Override
	public void save(UserProfile profile) {
		if(profile != null){
			userProfileDao.create(profile);
		}
	}

	@Override
	public void update(UserProfile profile) {
		if(profile != null){
			userProfileDao.update(profile);
		}
	}

	@Override
	public UserProfile getUserProfileBy(Long id) {
		return userProfileDao.get(id);
	}
	
	@Override
	public UserProfile getUserProfileByUserId(Long id){
		return userProfileDao.getBy(USER_ID, id);
	}

	@Override
	public UserProfile getUserProfileBy(Map<String, Object> values) {
		return userProfileDao.getBy(values, null);
	}

}

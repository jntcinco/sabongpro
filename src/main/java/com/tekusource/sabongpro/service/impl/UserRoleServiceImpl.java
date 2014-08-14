package com.tekusource.sabongpro.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.sabongpro.dao.UserRoleDao;
import com.tekusource.sabongpro.model.UserRole;
import com.tekusource.sabongpro.service.UserRoleService;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	public UserRole getUserRoleBy(String value) {
		return (UserRole) userRoleDao.getBy(USER_ROLE, value);
	}

	public UserRole getUserRoleBy(Map<String, Object> values) {
		return (UserRole) userRoleDao.getBy(values, null);
	}
}
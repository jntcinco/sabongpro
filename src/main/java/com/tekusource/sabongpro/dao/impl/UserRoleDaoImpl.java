package com.tekusource.sabongpro.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.sabongpro.dao.UserRoleDao;
import com.tekusource.sabongpro.model.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole, Long> implements UserRoleDao {

	public UserRoleDaoImpl() {
		super(UserRole.class);
	}
}

package com.tekusource.sabongpro.service;

import java.util.Map;

import com.tekusource.sabongpro.model.UserRole;

public interface UserRoleService {
	
	public static final String USER_ROLE = "user_role";

	public UserRole getUserRoleBy(String value);
	
	public UserRole getUserRoleBy(Map<String, Object> values);
}

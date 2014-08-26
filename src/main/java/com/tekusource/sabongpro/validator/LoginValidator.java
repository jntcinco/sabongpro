package com.tekusource.sabongpro.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.util.CommonUtil;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		String userName = user.getUserName();
		String password = user.getPassword();
		if(CommonUtil.isBlankOrNull(userName)) {
			errors.rejectValue("userName", "username.required");
		} 
		if(CommonUtil.isBlankOrNull(password)) {
			errors.rejectValue("password", "password.required");
		}
	}

}

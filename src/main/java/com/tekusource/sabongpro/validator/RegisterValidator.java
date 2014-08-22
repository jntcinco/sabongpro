package com.tekusource.sabongpro.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.util.CommonUtil;

public class RegisterValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		String email = user.getEmail();
		String username = user.getUserName();
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();
		if(CommonUtil.isBlankOrNull(email)) {
			errors.rejectValue("email", "email.required");
		} 
		if(CommonUtil.isBlankOrNull(username)) {
			errors.rejectValue("userName", "username.required");
		} 
		if(CommonUtil.isBlankOrNull(password)) {
			errors.rejectValue("password", "password.required");
		}
		if(CommonUtil.isBlankOrNull(confirmPassword)) {
			errors.rejectValue("confirmPassword", "confirmPassword.required");
		}
		if(!password.equals(confirmPassword)) {
			errors.rejectValue("confirmPassword", "confirmPassword.notMatch");
		}
	}
}

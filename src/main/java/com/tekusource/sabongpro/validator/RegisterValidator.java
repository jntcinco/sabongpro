package com.tekusource.sabongpro.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tekusource.sabongpro.model.User;

public class RegisterValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		String email = user.getEmail();
		String username = user.getUsername();
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();
		if(email.isEmpty() || email == null) {
			errors.rejectValue("email", "email.required");
		} 
		if(username.isEmpty() || username == null) {
			errors.rejectValue("username", "username.required");
		} 
		if(password.isEmpty() || password == null) {
			errors.rejectValue("password", "password.required");
		}
		if(confirmPassword.isEmpty() || confirmPassword == null) {
			errors.rejectValue("confirmPassword", "confirmPassword.required");
		}
		if(!password.equals(confirmPassword)) {
			errors.rejectValue("confirmPassword", "confirmPassword.notMatch");
		}
	}
}

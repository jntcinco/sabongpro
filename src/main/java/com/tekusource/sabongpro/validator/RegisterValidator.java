package com.tekusource.sabongpro.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname.required");

		String email = user.getEmail();
		String lastname = user.getLastname();
		String username = user.getUsername();
		String password = user.getPassword();
		String middlename = user.getMiddlename();
		String confirmPassword = user.getConfirmPassword();
		if(lastname.isEmpty() || lastname == null) {
			errors.rejectValue("lastname", "lastname.required");
		} 
		if(email.isEmpty() || email == null) {
			errors.rejectValue("email", "email.required");
		} 
		if(username.isEmpty() || username == null) {
			errors.rejectValue("username", "username.required");
		} 
		if(password.isEmpty() || password == null) {
			errors.rejectValue("password", "password.required");
		} 
		if(middlename.isEmpty() || middlename == null) {
			errors.rejectValue("middlename", "middlename.required");
		} 
		if(confirmPassword.isEmpty() || confirmPassword == null) {
			errors.rejectValue("confirmPassword", "confirmPassword.required");
		}
	}
}

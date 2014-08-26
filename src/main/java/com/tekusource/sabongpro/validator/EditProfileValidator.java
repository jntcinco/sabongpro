package com.tekusource.sabongpro.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tekusource.sabongpro.model.UserProfile;
import com.tekusource.sabongpro.util.CommonUtil;

public class EditProfileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserProfile.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserProfile profile = (UserProfile) target;
		
		if(CommonUtil.isBlankOrNull(profile.getFirstName())){
			errors.rejectValue("firstName", "first.name.required");
		}
		if(CommonUtil.isBlankOrNull(profile.getMiddleName())){
			errors.rejectValue("middleName", "middle.name.required");
		}
		if(CommonUtil.isBlankOrNull(profile.getLastName())){
			errors.rejectValue("lastName", "last.name.required");
		}
		if(CommonUtil.isBlankOrNull(profile.getStreet())){
			errors.rejectValue("street", "street.required");
		}
		if(CommonUtil.isBlankOrNull(profile.getCity())){
			errors.rejectValue("city", "city.required");
		}
		if(CommonUtil.isBlankOrNull(profile.getZip())){
			errors.rejectValue("zip", "zip.required");
		}
		if(CommonUtil.isBlankOrNull(profile.getContactNumber())){
			errors.rejectValue("contactNumber", "contact.number.required");
		}
	}

}

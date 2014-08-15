package com.tekusource.sabongpro.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tekusource.sabongpro.model.StreamingConfig;

public class StreamingConfigValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		return StreamingConfig.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		StreamingConfig config = (StreamingConfig) target;
		String description = config.getDescription();
		String url = config.getUrl();
		
		if(url.isEmpty() || url == null) {
			errors.rejectValue("url", "config.url.required");
		} 
		if(description.isEmpty() || description == null) {
			errors.rejectValue("description", "config.description.required");
		}
	}
}

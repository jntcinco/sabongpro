package com.tekusource.sabongpro.validator;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tekusource.sabongpro.model.Entry;
import com.tekusource.sabongpro.util.CommonUtil;

public class EntryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Entry.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Entry entry = (Entry) target;
		
		if(CommonUtil.isBlankOrNull(entry.getBloodLine())){
			errors.rejectValue("bloodLine", "entry.bloodline.required");
		}
		if(CommonUtil.isBlankOrNull(entry.getEntryName())){
			errors.rejectValue("entryName", "entry.owner.name.required");
		}
		if(CommonUtil.isBlankOrNull(entry.getFightWeight())){
			errors.rejectValue("fightWeight", "entry.fight.weight.required");
		}
		if(CommonUtil.isBlankOrNull(entry.getOwnerName())){
			errors.rejectValue("ownerName", "entry.owner.name.required");
		}
		if(CommonUtil.isBlankOrNull(entry.getSide())){
			errors.rejectValue("side", "entry.side.required");
		}
		if(entry.getFightNumber() == null){
			errors.rejectValue("fightNumber", "entry.fight.number.required");
		} else if(NumberUtils.isNumber(entry.getFightNumber())) {
			errors.rejectValue("fightNumber", "entry.fight.number.invalid");
		}
	}
}

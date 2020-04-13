package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Cause;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CauseValidator implements Validator {
	
	private static final String REQUIRED = "Required";

	@Override
	public boolean supports(Class<?> clazz) {
		return Cause.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cause cause = (Cause) target;
		String name;
		String description;
		Double targ;
		String organization;
		Integer integers;
		Integer decimals;
		
		name = cause.getName();
		description = cause.getDescription();
		targ = cause.getTarget();
		organization = cause.getOrganization();
		integers = targ.toString().indexOf(".");
		decimals = targ.toString().length() - integers - 1;
		
		if (!StringUtils.hasLength(name) || name.length()>50 || name.length()<3) {
			errors.rejectValue("name", REQUIRED+" and between 3 and 50 characters", REQUIRED+" and between 3 and 50 character");
		}
		
		if(description == null || description.equals("")) {
			errors.rejectValue("description", REQUIRED, REQUIRED);
		}
		
		if(integers > 100 || decimals > 2) {
			errors.rejectValue("target", REQUIRED, "The quantity must have less than 100 integers and 2 decimals");
		}
		
		if(!StringUtils.hasLength(organization)) {
			errors.rejectValue("organization", REQUIRED, "Must not be blank");
		}
		
	}

}

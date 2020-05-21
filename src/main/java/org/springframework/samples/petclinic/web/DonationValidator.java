package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Donation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DonationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Donation.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Donation donation = (Donation) target;
//		
//		System.out.println("dd");
//		if(donation == null || donation.equals("")) {
//			errors.rejectValue("amount", errorCode);
//		}
		try {
			Double.valueOf(donation.getAmount());
		} catch(NumberFormatException e) {
			errors.rejectValue("amount", "conversion-failed", "The input must be a decimal number");
		}
		
	}
	
}

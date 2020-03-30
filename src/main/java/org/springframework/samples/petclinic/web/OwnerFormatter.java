package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Component;

@Component
public class OwnerFormatter implements Formatter<Owner> {
	
	private final ClinicService clinicService;
	
	@Autowired
	public OwnerFormatter(ClinicService clinicService) {
		this.clinicService =clinicService;
	}

	@Override
	public String print(Owner owners, Locale locale) {
		return owners.getFirstName();
	}

	@Override
	public Owner parse(String text, Locale locale) throws ParseException {
		Collection<Owner> findOwner = this.clinicService.findAllOwner();
		for (Owner owner: findOwner) {
			if(owner.getFirstName().equals(text)) {
				return owner;
			}
		}
		throw new ParseException("Owner not found: " + text, 0);
	}

}
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/feature/L3-A3.2.2

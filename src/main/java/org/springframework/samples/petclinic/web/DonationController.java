package org.springframework.samples.petclinic.web;


import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DonationController {
	
	private final String ADD_VIEW = "donation/form";
	
	private final ClinicService clinicService;
	
	@Autowired
	public DonationController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@ModelAttribute("owners")
	public Collection<Owner> populateOwner() {
		return this.clinicService.findAllOwner();
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	
	@GetMapping("causes/{causeId}/donation/add")
	public String initCreationForm(final Map<String, Object> model) {
		Donation donation;
		donation = new Donation();
		Collection<Owner> owners = this.clinicService.findAllOwner();
		model.put("owner", owners);		
		model.put("donation", donation);
		
		return ADD_VIEW;
	}
	
	@PostMapping("causes/{causeId}/donation/add")
	public String proccessCreationForm(@Valid Donation donation, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.put("donation", donation);
			return ADD_VIEW;
		}
		
		clinicService.saveDonation(donation);
		
		return "redirect:/";
	}
	
}
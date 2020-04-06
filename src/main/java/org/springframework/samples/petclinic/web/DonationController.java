package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DonationController {

	private static final String ADD_VIEW = "donation/form";

	private final ClinicService clinicService;

	@Autowired
	public DonationController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@ModelAttribute("owners")
	public Collection<String> populateOwners() {
		Collection<String> res = new ArrayList<>();
		Collection<Owner> owners = this.clinicService.findAllOwners();
		for (Owner o : owners) {
			res.add(o.getFirstName() + " " + o.getLastName());
		}
		return res;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("causes/{causeId}/donations/new")
	public String initCreationForm(@PathVariable("causeId") final int causeId, final Map<String, Object> model) {
		Donation donation = new Donation();
		Cause cause = this.clinicService.findCauseById(causeId);
		if (cause != null) {
			donation.setCause(cause);
			donation.setDate(LocalDate.now());
			model.put("donation", donation);
			return ADD_VIEW;
		} else {
			throw new IllegalArgumentException("bad cause id.");
		}
	}

	@PostMapping("causes/{causeId}/donations/new")
	public String proccessCreationForm(@PathVariable("causeId") final int causeId, @Valid Donation donation, BindingResult result, ModelMap model) {
		Cause cause = this.clinicService.findCauseById(causeId);
		if (cause != null) {
			Double dineroDonado = cause.getDonations().stream().mapToDouble(x -> x.getAmount()).sum();
			Double dineroRestante = cause.getTarget() - dineroDonado;
			if (result.hasErrors()) {
				return ADD_VIEW;
			}else if (dineroRestante > 0) {
				donation.setCause(cause);
				donation.setDate(LocalDate.now());
				cause.addDonation(donation);
				clinicService.saveCause(cause);
				return "redirect:/causes/"+causeId+"/show";
			} else {
				result.rejectValue("owner", "causaCerrada", "The cause is closed because we already reached the target.");
				return ADD_VIEW;
			}
		} else {
			throw new IllegalArgumentException("bad cause id.");
		}
	}
}
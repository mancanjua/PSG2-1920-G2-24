package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cause")
public class CauseController {

	private final String CREATE_VIEW = "causes/form";
	
	@Autowired
	private ClinicService clinicService;
	
	@InitBinder("cause")
	public void initCauseBinder(WebDataBinder dataBinder) {
		dataBinder.addValidators(new CauseValidator());
	}
	
	@GetMapping("/create")
	public String initCreationForm(ModelMap model) {
		Cause cause;
		
		cause = new Cause();
		
		model.put("cause", cause);
		
		return CREATE_VIEW;
	}
	
	@PostMapping("/create")
	public String proccessCreationForm(@Valid Cause cause, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.put("cause", cause);
			return CREATE_VIEW;
		}
		
		clinicService.saveCause(cause);
		
		return "redirect:/";
	}
	
}

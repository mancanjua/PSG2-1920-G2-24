package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Causes;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/causes")
public class CauseController {

	private static final String CREATE_VIEW = "causes/form";
	
	@Autowired
	private ClinicService clinicService;
	
	@InitBinder("causes")
	public void initCauseBinder(WebDataBinder dataBinder) {
		dataBinder.addValidators(new CauseValidator());
	}
	
	
	
	@GetMapping(value = "/list")
		public String causeList(final ModelMap model) {
		String vista = "causes/causeList";
		Iterable<Cause> causes = this.clinicService.findCauses();
		model.addAttribute("causes", causes);
		return vista;
		}
	

	
	
	@GetMapping("/new")
	public String initCreationForm(ModelMap model) {
		Cause cause = new Cause();
		model.put("cause", cause);
		return CREATE_VIEW;
	}
	
	@PostMapping("/new")
	public String proccessCreationForm(@Valid Cause cause, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return CREATE_VIEW;
		}
		this.clinicService.saveCause(cause);
		return "redirect:/";
	}
	
	@GetMapping("/show/{causeId}")
	public ModelAndView showCause(@PathVariable("causeId") int causeId) {
		ModelAndView mav = new ModelAndView("causes/causeDetails");
		mav.addObject(this.clinicService.findCauseById(causeId));
		return mav;
	}

	
}

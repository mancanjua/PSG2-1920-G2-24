/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private static final String VIEWS_VET_CREATE_OR_UPDATE_FORM = "vets/createOrUpdateVetForm";

	private final ClinicService clinicService;

	@Autowired
	public VetController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}
	
	@GetMapping("/vets")
	public String showVetList(final Map<String, Object> model) {
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping("/vets.xml")
	public @ResponseBody Vets showResourcesVetList() {
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		return vets;
	}

	@GetMapping("/vets/{vetId}/delete")
	public String deleteVetById(@PathVariable("vetId") final int vetId, final Map<String, Object> model) {
		this.clinicService.findVetById(vetId).removeAllSpecialties();
		this.clinicService.deleteVetById(vetId);
		return this.showVetList(model);
	}

	@GetMapping("/vets/{vetId}")
	public ModelAndView showVet(@PathVariable("vetId") final int vetId) {
		ModelAndView mav = new ModelAndView("vets/vetDetails");
		mav.addObject(this.clinicService.findVetById(vetId));
		return mav;
	}

	@GetMapping("/vets/new")
	public String initCreationForm(final Map<String, Object> model) {
		Vet vet = new Vet();
		Collection<Specialty> specialties = this.clinicService.findAllSpecialties();
		model.put("specialties", specialties);
		model.put("vet", vet);
		return VetController.VIEWS_VET_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/vets/new")
	public String processCreationForm(@Valid final Vet vet, final BindingResult result) {
		if (result.hasErrors()) {
			return VetController.VIEWS_VET_CREATE_OR_UPDATE_FORM;
		} else {
			this.clinicService.saveVet(vet);
			return "redirect:/vets/" + vet.getId();
		}
	}

	@GetMapping("/vets/{vetId}/edit")
	public String initUpdateVetForm(@PathVariable("vetId") final int vetId, final ModelMap model) {
		Vet vet = this.clinicService.findVetById(vetId);
		Collection<Specialty> specialties = this.clinicService.findAllSpecialties();
		model.put("specialties", specialties);
		model.put("vet", vet);
		return VetController.VIEWS_VET_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/vets/{vetId}/edit")
	public String processUpdateVetForm(@Valid final Vet vet, final BindingResult result,
			@PathVariable("vetId") final int vetId, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("vet", vet);
			return VetController.VIEWS_VET_CREATE_OR_UPDATE_FORM;
		} else {
			this.clinicService.saveVet(vet);
			return "redirect:/vets/{vetId}";
		}
	}

	@GetMapping("/vets/{vetId}/removeSpecialty/{specialtyId}")
	public String deleteSpecialityById(@PathVariable("specialtyId") final int specialtyId,
			@PathVariable("vetId") final int vetId) {
		Specialty sp = this.clinicService.findSpecialtyById(specialtyId);
		Vet vet = this.clinicService.findVetById(vetId);
		vet.removeSpecialty(sp);
		this.clinicService.saveVet(vet);
		return "redirect:/vets/{vetId}";
	}
}
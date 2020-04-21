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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Hotel;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	private static final String REDIRECT = "redirect:/owners/{ownerId}";

	private final ClinicService clinicService;

	@Autowired
	public PetController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return this.clinicService.findPetTypes();
	}

	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") final int ownerId) {
		return this.clinicService.findOwnerById(ownerId);
	}

	@InitBinder("owner")
	public void initOwnerBinder(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("pet")
	public void initPetBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetValidator());
	}

	@GetMapping(value = "/pets/new")
	public String initCreationForm(final Owner owner, final ModelMap model) {
		Pet pet = new Pet();
		owner.addPet(pet);
		model.put("pet", pet);
		return PetController.VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/pets/new")
	public String processCreationForm(final Owner owner, @Valid final Pet pet, final BindingResult result,
			final ModelMap model) {
		if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}
		if (result.hasErrors()) {
			model.put("pet", pet);
			return PetController.VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		} else {
			owner.addPet(pet);
			this.clinicService.savePet(pet);
			return REDIRECT;
		}
	}

	@GetMapping(value = "/pets/{petId}/edit")
	public String initUpdateForm(@PathVariable("petId") final int petId, final ModelMap model) {
		Pet pet = this.clinicService.findPetById(petId);
		model.put("pet", pet);
		return PetController.VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/pets/{petId}/edit")
	public String processUpdateForm(@Valid final Pet pet, final BindingResult result, final Owner owner,
			final ModelMap model) {
		if (result.hasErrors()) {
			model.put("pet", pet);
			return PetController.VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		} else {
			pet.setOwner(owner);
			this.clinicService.savePet(pet);
			return REDIRECT;
		}
	}

	@GetMapping(value = "/pets/{petId}/remove")
	public String processPetRemoval(@PathVariable("petId") final int petId, final Owner owner, final ModelMap model) {
		Pet pet = this.clinicService.findPetById(petId);
		if (pet != null && pet.getOwner().equals(owner)) {
			Collection<Visit> visits = this.clinicService.findVisitsByPetId(pet.getId());
			Collection<Hotel> hotels = this.clinicService.findHotelsByPetId(pet.getId());
			this.clinicService.removePetVisits(visits);
			for(Hotel h : hotels) {
				this.clinicService.removeHotel(h);
			}
			this.clinicService.removePet(pet);
			return REDIRECT;
		} else {
			throw new IllegalArgumentException("Bad pet id or the pet does not belong to the active owner.");
		}
	}

	@GetMapping(value = "/pets/{petId}/removeAllVisits")
	public String processPetVisitsRemoval(@PathVariable("petId") final int petId, final Owner owner,
			final ModelMap model) {
		Pet pet = this.clinicService.findPetById(petId);
		if (pet != null && pet.getOwner().equals(owner)) {
			Collection<Visit> visits = this.clinicService.findVisitsByPetId(pet.getId());
			this.clinicService.removePetVisits(visits);
			return REDIRECT;
		} else {
			throw new IllegalArgumentException(
					"Bad pet id, the pet does not belong to the active owner or bad visit id.");
		}
	}

	@GetMapping(value = "/pets/{petId}/{visitId}/removeVisit")
	public String processPetVisitRemoval(@PathVariable("petId") final int petId,
			@PathVariable("visitId") final int visitId, final Owner owner, final ModelMap model) {
		Pet pet = this.clinicService.findPetById(petId);
		Visit visita = this.clinicService.findVisitById(visitId);
		if (pet != null && pet.getOwner().equals(owner) && pet.getVisits().contains(visita)) {
			this.clinicService.removePetVisit(visita);
			return REDIRECT;
		} else {
			throw new IllegalArgumentException(
					"Bad pet id, the pet does not belong to the active owner or bad visit id.");
		}
	}

	@GetMapping(path = "/pets/{petId}/hotels/remove/{hotelId}")
	public String processHotelRemoval(@PathVariable("hotelId") int hotelId, @PathVariable("petId") int petId,
			final Owner owner, final ModelMap model) {
		Hotel hotel = this.clinicService.findHotelById(hotelId);
		if (hotel != null && hotel.getPet().equals(this.clinicService.findPetById(petId))
				&& hotel.getPet().getOwner().equals(owner)) {
			this.clinicService.removeHotel(hotel);
			return REDIRECT;
		} else {
			throw new IllegalArgumentException("Booking not found or bad pet!");
		}
	}
}
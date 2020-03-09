package org.springframework.samples.petclinic.web;
import java.util.Collection;
import java.util.Map;
 
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Hotel;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.util.HotelDateConstraints;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
 
@Controller
public class HotelController {
    private final ClinicService clinicService;
 
    @Autowired
    public HotelController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }
 
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("hotel")
    public Hotel loadPetWithHotel(@PathVariable("petId") int petId) {
        Pet pet = this.clinicService.findPetById(petId);
        Hotel hotel = new Hotel();
        pet.addHotel(hotel);
        return hotel;
    }
 
    @GetMapping(value = "/owners/*/pets/{petId}/hotels/new")
    public String initNewHotelForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        return "pets/createOrUpdateHotelForm";
    }
 
    @PostMapping(value = "/owners/{ownerId}/pets/{petId}/hotels/new")
    public String processNewHotelForm(@Valid Hotel hotel, BindingResult result) {
        Pet pet = hotel.getPet();
        if (pet != null) {
            Collection<Hotel> hotels = this.clinicService.findHotelsByPetId(hotel.getPet().getId());
            if (!(hotel.getStartDate() == null || hotel.getEndDate() == null)) {
               
                if (HotelDateConstraints.invalidDates(hotel)) {
                    result.rejectValue("endDate", "startDateIsAfterEndDate",
                            "The start date must be before the end date");
                }
                
                //AQUI HAY QUE AÑADIR LA RESTRICCION DE FECHAS EN INTERVALOS
            }
        }
 
        if (result.hasErrors()) {
            return "pets/createOrUpdateHotelForm";
        } else {
 
            this.clinicService.saveHotel(hotel);
            return "redirect:/owners/{ownerId}";
        }
    }
 
    @GetMapping(value = "/owners/*/pets/{petId}/hotels")
    public String showHotels(@PathVariable int petId, Map<String, Object> model) {
        model.put("hotels", this.clinicService.findPetById(petId).getHotels());
        return "hotelList";
    }
 
    @GetMapping(value = "/owners/{ownerId}/pets/{petId}/hotels/{hotelId}/delete")
    public String delete(@PathVariable("hotelId") int hotelId, @PathVariable("petId") int petId) {
 
        Pet pet = this.clinicService.findPetById(petId);
        Hotel hotel = this.clinicService.findHotelById(hotelId);
        pet.deleteHotel(hotel);
        this.clinicService.savePet(pet);
 
        return "redirect:/owners/{ownerId}";
 
    }
}
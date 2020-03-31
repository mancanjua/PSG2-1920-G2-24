

package org.springframework.samples.petclinic.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Hotel;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.CauseRepository;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.samples.petclinic.repository.HotelRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClinicService {

	private PetRepository		petRepository;

	private VetRepository		vetRepository;

	private OwnerRepository		ownerRepository;

	private VisitRepository	visitRepository;
	
	private HotelRepository hotelRepository;

	private SpecialtyRepository	specialtyRepository;

	@Autowired
	private CauseRepository causeRepository;
	
	@Autowired 
	private DonationRepository donationRepository;
	

	@Autowired
	public ClinicService(final PetRepository petRepository, final VetRepository vetRepository, final OwnerRepository ownerRepository, final VisitRepository visitRepository, final SpecialtyRepository specialtyRepository, final HotelRepository hotelRepository) {
		this.petRepository = petRepository;
		this.vetRepository = vetRepository;
		this.ownerRepository = ownerRepository;
		this.visitRepository = visitRepository;
		this.hotelRepository = hotelRepository;
		this.specialtyRepository = specialtyRepository;
	}

	@Transactional(readOnly = true)
	public Collection<PetType> findPetTypes() throws DataAccessException {
		return this.petRepository.findPetTypes();
	}
	
	@Transactional(readOnly = true)
	public Collection<Owner> findAllOwners() {
		return this.ownerRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Owner findOwnerById(final int id) throws DataAccessException {
		return this.ownerRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Owner> findOwnerByLastName(final String lastName) throws DataAccessException {
		return this.ownerRepository.findByLastName(lastName);
	}

	@Transactional
	public void saveOwner(final Owner owner) throws DataAccessException {
		this.ownerRepository.save(owner);
	}

	@Transactional
	public void removeOwner(final Owner owner) throws DataAccessException {
		this.ownerRepository.removeOwner(owner.getId());
	}
	
	@Transactional
	public void saveVisit(final Visit visit) throws DataAccessException {
		this.visitRepository.save(visit);
	}

	@Transactional(readOnly = true)
	public Pet findPetById(final int id) throws DataAccessException {
		return this.petRepository.findById(id);
	}

	@Transactional
	public void savePet(final Pet pet) throws DataAccessException {
		this.petRepository.save(pet);
	}
	
	@Transactional
	public void removePet(final Pet pet) throws DataAccessException {
		this.petRepository.removePet(pet.getId());
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "vets")
	public Collection<Vet> findVets() throws DataAccessException {
		return this.vetRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Vet findVetById(final int id) throws DataAccessException {
		return this.vetRepository.findById(id);
	}
	@Transactional(readOnly = true)
	public Visit findVisitById(final int visitId) throws DataAccessException {
		return this.visitRepository.findById(visitId);
	}
	@Transactional
	public void saveVet(final Vet vet) throws DataAccessException {
		this.vetRepository.save(vet);
	}

	public Collection<Visit> findVisitsByPetId(final int petId) {
		return this.visitRepository.findByPetId(petId);
	}

	public void removePetVisit(final Visit visit) {
		this.visitRepository.removePetVisit(visit.getId());
	}

	@Transactional
	public void deleteVetById(final int vetId) {
		this.vetRepository.deleteById(vetId);
	}


	@Transactional
	public void saveSpecialty(@Valid final Specialty specialty) {
		this.specialtyRepository.save(specialty);

	}

	@Transactional(readOnly = true)
	public Collection<Specialty> findSpecialitiesByVetId(final int vetId) {
		return this.specialtyRepository.findByVetId(vetId);
	}

	@Transactional(readOnly = true)
	public Specialty findSpecialtyById(final int specialtyId) {
		return this.specialtyRepository.findById(specialtyId);
	}

	@Transactional(readOnly = true)
	public Collection<Specialty> findAllSpecialties() {
		return this.specialtyRepository.findAll();
	}

	@Transactional
	public void deleteSpecialtyById(final int specialtyId) {
		for (Vet vet : this.vetRepository.findBySpecialtyId(specialtyId)) {
			vet.removeSpecialty(this.findSpecialtyById(specialtyId));
		}
		this.specialtyRepository.deleteById(specialtyId);
	}
	
	public void removePetVisits(final Collection<Visit> visits) {
		for (Visit v : visits) {
			this.visitRepository.removePetVisit(v.getId());
		}
	}
	
	@Transactional
    public void saveHotel(Hotel hotel)  {
        this.hotelRepository.save(hotel);
    }
 
    @Transactional
    public void removeHotel(final Hotel hotel) throws DataAccessException {
	    this.hotelRepository.removeHotel(hotel.getId());
	
    }
 
    @Transactional(readOnly = true)
    public Hotel findHotelById(int hotelId) {
        return this.hotelRepository.findByHotelId(hotelId);
    }
 
    @Transactional(readOnly = true)
    public Collection<Hotel> findHotelsByPetId(final int petId) {
        return this.hotelRepository.findByPetId(petId);
	}
    
    @Transactional(readOnly = true)
    public Cause findCauseById(int causeId) {
        return this.causeRepository.findById(causeId);
    }
    
    @Transactional
    public void saveCause(Cause cause) {
    	this.causeRepository.save(cause);
    }

    @Transactional
	public void saveDonation(Donation donation) {
		this.donationRepository.save(donation);
		
	}
}
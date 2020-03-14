package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;



import org.springframework.dao.DataAccessException;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;

public interface SpringDataSpecialtyRepository extends SpecialtyRepository, Repository<Specialty, Integer> {

	@Override
	@Query("SELECT vet.specialties FROM Vet vet where vet.id=?1")
	Collection<Specialty> findByVetId(int id) throws DataAccessException;
	
	
}
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Specialty;

public interface SpecialtyRepository {

	Specialty findById(int id);

	void save(Specialty specialty) throws DataAccessException;

	Collection<Specialty> findByVetId(int id) throws DataAccessException;

	Collection<Specialty> findAll();

	void deleteById(int id);

}
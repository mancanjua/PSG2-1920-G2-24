package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.samples.petclinic.model.Specialty;

public interface SpecialtyRepository {

	Specialty findById(int id);

	void save(Specialty specialty);

	Collection<Specialty> findByVetId(int id);

	Collection<Specialty> findAll();

	void deleteById(int id);
}
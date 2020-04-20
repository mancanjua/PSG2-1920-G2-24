
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.samples.petclinic.model.Vet;

public interface VetRepository {

	/**
	 * Retrieve all <code>Vet</code>s from the data store.
	 * 
	 * @return a <code>Collection</code> of <code>Vet</code>s
	 */
	Collection<Vet> findAll();
	
	void deleteById(int id);

	Vet findById(int id);

	void save(Vet vet);

	Collection<Vet> findBySpecialtyId(int id);
}
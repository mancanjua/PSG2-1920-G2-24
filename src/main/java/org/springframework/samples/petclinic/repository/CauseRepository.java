package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.samples.petclinic.model.Cause;

public interface CauseRepository {
	
	Collection<Cause> findAll();

    Cause findById(int id);
    
    void deleteById(int id);
    
    void save(Cause cause);
}

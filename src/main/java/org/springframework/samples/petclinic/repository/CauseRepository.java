package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;

public interface CauseRepository {
	
	Collection<Cause> findAll() throws DataAccessException;

    Cause findById(int id) throws DataAccessException;
    
    void deleteById(int id) throws DataAccessException;
    
    void save(Cause cause) throws DataAccessException;
}

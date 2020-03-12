package org.springframework.samples.petclinic.repository.springdatajpa;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Hotel;
import org.springframework.samples.petclinic.repository.HotelRepository;

public interface SpringDataHotelRepository extends HotelRepository, Repository<Hotel, Integer> {
	
	@Override
	@Transactional
	@Modifying
	@Query("DELETE FROM Hotel WHERE id = ?1")
	void removeHotel(int hotelId) throws DataAccessException;

}

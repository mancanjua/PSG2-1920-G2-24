package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Hotel;

public interface HotelRepository {
	 
    void save(Hotel hotel);
 
    List<Hotel> findByPetId(Integer petId);
 
    @Query("SELECT h FROM Hotel h where h.id=:hotelId")
    Hotel findByHotelId(@Param(value = "hotelId") int hotelId);

    void removeHotel(int hotelId);
}
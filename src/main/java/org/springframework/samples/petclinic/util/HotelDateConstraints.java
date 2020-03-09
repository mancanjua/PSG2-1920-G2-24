package org.springframework.samples.petclinic.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import org.springframework.samples.petclinic.model.Hotel;
 
public class HotelDateConstraints {
 
    private HotelDateConstraints() {
        new HotelDateConstraints();
    }
 
    //Comprueba que la fecha de fin no sea anterior a la fecha de comienzo
    public static boolean invalidDates(Hotel hotel) {
        boolean invalidDate = false;
        if (hotel.getStartDate().isAfter(hotel.getEndDate())) {
            invalidDate = true;
        }
        return invalidDate;
    }
    
    //Añadir aqui el método de validar los intervalos de las fechas.
}
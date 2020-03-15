package org.springframework.samples.petclinic.util;


import java.time.LocalDate;

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
    public static boolean invalidDates1(Hotel hotelPrevio, Hotel hotelNew) {
    	boolean date = false;
    	if(hotelPrevio.getEndDate().equals(hotelNew.getStartDate()) 
    			){
    		date = true;
    	}
    	return date;
    }
    public static boolean equalStart(Hotel hotelPrevio, Hotel hotelNew) {
    	boolean date = false;
    	if(hotelPrevio.getStartDate().equals(hotelNew.getStartDate()) 
    			){
    		date = true;
    	}
    	
    	return date;
    }
    
    public static boolean equalEnd(Hotel hotelPrevio, Hotel hotelNew) {
    	boolean date = false;
    	if(hotelPrevio.getEndDate().equals(hotelNew.getEndDate()) 
    			){
    		date = true;
    	}
    	
    	return date;
    }
    public static boolean invalidDates2(Hotel hotelPrevio, Hotel hotelNew) {
    	boolean date = false;
    	if(hotelPrevio.getStartDate().equals(hotelNew.getEndDate()) 
    			|| hotelPrevio.getEndDate().equals(hotelNew.getEndDate())){
    		date = true;
    	}
    	return date;
    }
    
    public static boolean invalidDates3(Hotel hotelPrevio, Hotel hotelNew) {
    	boolean date = false;
    	if((hotelNew.getStartDate().isAfter(hotelPrevio.getStartDate()) 
    			&& hotelNew.getStartDate().isBefore(hotelPrevio.getEndDate())) 
    			|| (hotelNew.getEndDate().isBefore(hotelPrevio.getEndDate()) 
    					&& hotelNew.getEndDate().isAfter(hotelPrevio.getStartDate()))){
    		date = true;
    	}
    	return date;
    }
    
    public static boolean invalidDates4(Hotel hotelPrevio, Hotel hotelNew) {
    	boolean date = false;
    	if(hotelNew.getStartDate().isBefore(hotelPrevio.getStartDate()) 
    			&& hotelNew.getEndDate().isAfter(hotelPrevio.getEndDate())){
    		date = true;
    	}
    	return date;
    }
    
    public static boolean invalidDates5(Hotel hotel) {
    	boolean date = false;
    	if(hotel.getStartDate().isBefore(LocalDate.now())) {
    		date= true;
    		
    	}
    	
    	return date;
    }
    
}
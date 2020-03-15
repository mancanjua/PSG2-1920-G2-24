package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name = "hotels")
public class Hotel extends BaseEntity{
	
	
	//Descripicion de la reserva.
	@NotBlank
    @Column(name = "description")
    private String description;
 
	
	//Fecha de inicio de la reserva en el hotel.
    @NotNull
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;
 
    
    //Fecha de fin de la reserva en el hotel.
    @NotNull
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate endDate;
   
    //Mascota
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    
    //GETTERS AND SETTERS
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}




	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
   
    
 
    
}

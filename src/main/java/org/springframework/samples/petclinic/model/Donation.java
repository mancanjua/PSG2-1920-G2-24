package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "donations")
public class Donation extends NamedEntity {
	
	
	@OneToOne
	private Owner owners;
	
	public void setOwners(final Owner owners) {
		this.owners = owners;
	}
	

	public Owner getOwners() {
		return owners;
	}
	
	@Column(name = "amount")
	@Digits(fraction = 2, integer = 100)
	private Double amount;
	
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate date;
	
		
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
		
}

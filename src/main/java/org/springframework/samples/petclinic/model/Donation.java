package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity {
	
	@Column(name = "amount")
	@Digits(fraction = 2, integer = 100)
	@DecimalMin(inclusive = true, value = "0.01")
	@NotNull
	private Double amount;
	
	@Column(name = "creation_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	@PastOrPresent
	private LocalDate date;
	
	@NotNull
	@Column(name = "owner")
	private String owner;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cause_id")
	private Cause cause;
	
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}
		
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
	
	public void setCause(final Cause cause) {
		this.cause = cause;
	}

	public Cause getCause() {
		return cause;
	}
}

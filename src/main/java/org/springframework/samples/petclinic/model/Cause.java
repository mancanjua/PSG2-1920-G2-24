package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "causes")
public class Cause extends NamedEntity {
	
	@Column(name = "target")
	@Digits(fraction = 2, integer = 100)
	private Double target;
	
	@NotBlank
	@Column(name = "organization")
	private String organization;
	
}

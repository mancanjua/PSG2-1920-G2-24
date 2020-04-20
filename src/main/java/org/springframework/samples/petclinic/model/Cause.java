package org.springframework.samples.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "causes")
public class Cause extends NamedEntity {
	
	@NotBlank
	@Column(name = "description")
	private String description;
	
	@NotNull
	@Column(name = "target")
	@Digits(fraction = 2, integer = 100)
	private Double target;
	
	@NotBlank
	@Column(name = "organization")
	private String organization;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cause", fetch = FetchType.EAGER)
    private Set<Donation> donations;
	
		
	public Double getPresentBudget() {
		return this.donations.stream().mapToDouble(Donation::getAmount).sum();
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Double getTarget() {
		return target;
	}

	public void setTarget(Double target) {
		this.target = target;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public Set<Donation> getDonations() {
		return this.donations;
	}

	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}
	
	public void addDonation(Donation donation) {
		if(this.donations == null) {
			this.donations = new HashSet<>();
		}
		this.getDonations().add(donation);
		donation.setCause(this);
	}
}

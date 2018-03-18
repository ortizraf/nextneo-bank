package com.nextneo.bank.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.nextneo.bank.models.BaseModel;

@Entity
@Table(name="branch", indexes = { @Index(name="IDX_BRANCH", columnList="number") })
public class Branch extends BaseModel {
	
	@NotNull
	@Column(name="number")
	private String number;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	public Branch() {
		
	}
	
	public Branch(Long id) {
		this.setId(id);
	}

	public Branch(Long id, String agencyNumber) {
		this.setId(id);
		this.number = agencyNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

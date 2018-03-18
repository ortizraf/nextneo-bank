package com.nextneo.bank.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.nextneo.bank.models.BaseModel;

@Entity
@Table(name="currency")
public class Currency extends BaseModel {
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="symbol")
	private String symbol;
	
	@NotNull
	@Column(name="code")
	private String code;
	
	public Currency() {

	}
	
	public Currency(Long id) {
		setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

package com.nextneo.bank.integration.dto;

import java.math.BigDecimal;
import java.util.Calendar;

import com.nextneo.bank.integration.dto.enums.AccountMovementTypeDto;

public class AccountMovementDto {
	
	private Calendar launchDate;
	
	private BigDecimal value;
	
	private String auth;
	
	private String description;
	
	private BigDecimal accountBalance;
	
	private AccountMovementTypeDto type;

	private long accountId;
	
	public AccountMovementDto(){
		
	}
	
	public Calendar getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Calendar launchDate) {
		this.launchDate = launchDate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public AccountMovementTypeDto getType() {
		return type;
	}

	public void setType(AccountMovementTypeDto type) {
		this.type = type;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}

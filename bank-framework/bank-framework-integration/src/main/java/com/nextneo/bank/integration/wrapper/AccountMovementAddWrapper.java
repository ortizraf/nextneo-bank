package com.nextneo.bank.integration.wrapper;

import java.math.BigDecimal;

import com.nextneo.bank.integration.dto.enums.AccountMovementTypeDto;

public class AccountMovementAddWrapper {
	
	long accountId;
	
	BigDecimal value;
	
	String description;
	
	AccountMovementTypeDto type;
	
	public AccountMovementAddWrapper(){
		
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccountMovementTypeDto getType() {
		return type;
	}

	public void setType(AccountMovementTypeDto type) {
		this.type = type;
	}
	
	
}

package com.nextneo.bank.models.enums;

import java.util.Locale;

import com.nextneo.bank.utils.messages.MessageProperties;

public enum AccountMovementType {
	
	DEBIT("account_movement_debit"),
	CREDIT("account_movement_credit");
	
	private AccountMovementType(String name){
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return MessageProperties.getMessage(name);
	}
	
	public String getName(Locale locale) {
		return MessageProperties.getMessage(name, locale);
	}

	public void setName(String name) {
		this.name = name;
	}

}

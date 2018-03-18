package com.nextneo.bank.models.enums;

import java.util.Locale;

import com.nextneo.bank.utils.messages.MessageProperties;

public enum AccountType {
	
	CHECKING_ACCOUNT("account_checking_account"),
	SAVINGS_ACCOUNT("account_savings_account");
	
	private AccountType(String name){
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

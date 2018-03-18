package com.nextneo.bank.integration.dto.enums;

import java.util.Locale;

import com.nextneo.bank.utils.messages.MessageProperties;

public enum AccountTypeDto {
	
	CHECKING_ACCOUNT("account_checking_account"),
	SAVINGS_ACCOUNT("account_savings_account");
	
	private AccountTypeDto(String name){
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

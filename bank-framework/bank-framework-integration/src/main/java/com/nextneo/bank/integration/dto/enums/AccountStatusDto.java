package com.nextneo.bank.integration.dto.enums;

import java.util.Locale;

import com.nextneo.bank.utils.messages.MessageProperties;

public enum AccountStatusDto {
	
	ACTIVE("account_status_active"),
	INACTIVE("account_status_inactive");
	
	private AccountStatusDto(String name){
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

package com.nextneo.bank.integration.dto.enums;

import java.util.Locale;

import com.nextneo.bank.utils.messages.MessageProperties;

public enum UserTypeDto {
	
	CUSTOMER("user_customer"),
	MANAGER("user_manager");
	
	private UserTypeDto(String name){
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

package com.nextneo.bank.utils.messages;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageProperties {
	
	public static String getMessage(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		return bundle.getString(key);
	}
	
	public static String getMessageBR(String key) {
		Locale ptBR = new Locale("pt","BR");
		ResourceBundle bundle = ResourceBundle.getBundle("messages", ptBR);
		return bundle.getString(key);
	}
	
	public static String getMessage(String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		return bundle.getString(key);
	}

}

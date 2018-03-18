package com.nextneo.bank.utils.format;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class FormatUtils {
	
	/**
	 * Convert date to text value - Example: Date to Sunday, October 1, 2018
	 * @param date
	 * @return
	 */
	public static String getDateFormat(Date date) {
		Locale enUS = new Locale("en", "US");
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, enUS);
		return dateFormat.format(date);
	}
	
	/**
	 * Convert date to text value - Example: Date to 7:06:00 AM
	 * @param date
	 * @return
	 */
	public static String getTimeFormat(Date date) {
		Locale enUS = new Locale("en", "US");
		DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, enUS);
		return timeFormat.format(date);
	}
	
	/**
	 * Convert value to text value - Example: 666.66 to 666.66
	 * @param value
	 * @return
	 */
	public static String getNumberFormat(BigDecimal value) {
		Locale enUS = new Locale("en", "US");
		NumberFormat numberFormat = NumberFormat.getNumberInstance(enUS); //para números
		return numberFormat.format(value);
	}
	
	/**
	 * Convert value to text value - Example: 666.66 to $666.66
	 * @param value
	 * @return
	 */
	public static String getCurrencyFormat(BigDecimal value) {
		Locale enUS = new Locale("en", "US");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(enUS);
		return currencyFormat.format(value);
	}
	
	/**
	 * Convert date to text value - Example: Date to Domingo, 1 de Outubro de 2018
	 * @param date
	 * @return
	 */
	public static String getDateFormatBR(Date date) {
		Locale ptBR = new Locale("pt", "BR");
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, ptBR);
		return dateFormat.format(date);
	}
	
	/**
	 * Convert date to text value - Example: Date to 07:06:00
	 * @param date
	 * @return
	 */
	public static String getTimeFormatBR(Date date) {
		Locale ptBR = new Locale("pt", "BR");
		DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, ptBR);
		return timeFormat.format(date);
	}
	
	/**
	 * Convert value to text value - Example: 666.66 to 666,66
	 * @param value
	 * @return
	 */
	public static String getNumberFormatBR(BigDecimal value) {
		Locale ptBR = new Locale("pt", "BR");
		NumberFormat numberFormat = NumberFormat.getNumberInstance(ptBR);
		return numberFormat.format(value);
	}
	
	/**
	 * Convert value to text value - Example: 666.66 to R$ 666,66
	 * @param value
	 * @return
	 */
	public static String getCurrencyFormatBR(BigDecimal value) {
		Locale ptBR = new Locale("pt", "BR");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(ptBR);
		return currencyFormat.format(value);
	}
	
	/**
	 * Convert text value to value - Example: 666,66 to 666.66
	 * @param textValue
	 * @return
	 */
	public static BigDecimal getNumberFormatBR(String textValue) {
		Locale ptBR = new Locale("pt", "BR");
		NumberFormat numberFormat = NumberFormat.getNumberInstance(ptBR);
		
		BigDecimal value = null;
		try {
			value = new BigDecimal(numberFormat.parse(textValue).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		System.out.println(value);
		return value;
	}
	
	/**
	 * Convert text value to value - Example: R$ 666,66 to 666.66
	 * @param textValue
	 * @return
	 */
	public static BigDecimal getCurrencyFormatBR(String textValue) {
		Locale ptBR = new Locale("pt", "BR");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(ptBR);
		
		BigDecimal currency = null;
		try {
			currency = new BigDecimal(currencyFormat.parse(textValue).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return currency;
	}

}

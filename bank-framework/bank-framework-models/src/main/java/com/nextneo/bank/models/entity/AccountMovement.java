package com.nextneo.bank.models.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.nextneo.bank.models.BaseModel;
import com.nextneo.bank.models.enums.AccountMovementType;

@Entity
@Table(name="account_movement")
public class AccountMovement extends BaseModel {
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="launch_date")
	private Calendar launchDate;
	
	@NotNull
	@Column(name="value", scale = 2)
	private BigDecimal value;
	
	@NotNull
	@Column(name="auth", unique=true)
	private String auth;
	
	@NotNull
	@Column(name="description")
	private String description;
	
	@NotNull
	@Column(name="account_balance", scale = 2)
	private BigDecimal accountBalance;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private AccountMovementType type;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Account account;
	
	public AccountMovement(){
		
	}
	
	public AccountMovement(long accountId, BigDecimal value, String description, BigDecimal accountBalance, AccountMovementType type){
		this.account = new Account(accountId);
		this.value = value;
		this.auth = String.valueOf(generateAuth());
		this.description = description;
		this.accountBalance = accountBalance;
		this.type = type;
		if(this.type==AccountMovementType.DEBIT) {
			this.accountBalance = this.accountBalance.subtract(this.value);		
		} else if(this.type==AccountMovementType.CREDIT){
			this.accountBalance = this.accountBalance.add(this.value);		
		}
		if(this.launchDate == null){
			this.launchDate = Calendar.getInstance();
		}
	}
	
	public Calendar getLaunchDate() {
		return launchDate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public String getAuth() {
		return auth;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public AccountMovementType getType() {
		return type;
	}

	public Account getAccount() {
		return account;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		if (launchDate == null) {
			launchDate = Calendar.getInstance();
		}
	}
	
	private int generateAuth() {
		int unique_id= (int) ((new Date().getTime()+this.account.getId() / 1000L) % Integer.MAX_VALUE); 

		return unique_id;
	}
	
	

}

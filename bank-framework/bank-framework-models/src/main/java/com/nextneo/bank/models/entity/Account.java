package com.nextneo.bank.models.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.nextneo.bank.models.BaseModel;
import com.nextneo.bank.models.enums.AccountStatus;
import com.nextneo.bank.models.enums.AccountType;

@Entity
@Table(name="account", indexes = { @Index(name="IDX_ACCOUNT", columnList="account_number,status,type") })
public class Account extends BaseModel {
	
	@NotNull
	@ManyToOne
	private Branch accountBranch;
	
	@NotNull
	@Column(name="account_number")
	private String accountNumber;
	
	@NotNull
	@Column(name="account_digit")
	private String accountDigit;
	
	@NotNull 
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private AccountStatus status = AccountStatus.INACTIVE;
	
	@NotNull 
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private AccountType type;
	
	@NotNull
	@ManyToOne
	private Currency currency;
	
	@OneToMany(mappedBy="account", fetch=FetchType.LAZY)
	private List<AccountMovement> movements;
	
	@ManyToMany
	@JoinTable(name = "account_customer", joinColumns = { @JoinColumn(name = "account_id") }, inverseJoinColumns = {
			@JoinColumn(name = "customer_id") })
    private Set<Customer> customers;

	public Branch getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(Branch accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountDigit() {
		return accountDigit;
	}

	public void setAccountDigit(String accountDigit) {
		this.accountDigit = accountDigit;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<AccountMovement> getMovements() {
		return movements;
	}

	public void setMovements(List<AccountMovement> movements) {
		this.movements = movements;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Account(){
		
	}
	
	public Account(Long id){
		this.setId(id);
	}
	
	public Account(Branch accountBranch, String accountNumber, String accountDigit, AccountStatus status, AccountType type){
		this.accountBranch = accountBranch;
		this.accountNumber = accountNumber;
		this.accountDigit = accountDigit;
		this.status = status;
		this.type = type;
	}
	
	public Account(Branch accountBranch, String accountNumber, String accountDigit, AccountStatus status, AccountType type, Currency currency){
		this.accountBranch = accountBranch;
		this.accountNumber = accountNumber;
		this.accountDigit = accountDigit;
		this.status = status;
		this.type = type;
		this.currency = currency;
	}

}

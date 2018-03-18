package com.nextneo.bank.integration.dto;

import java.util.Set;

import com.nextneo.bank.integration.dto.enums.AccountStatusDto;
import com.nextneo.bank.integration.dto.enums.AccountTypeDto;

public class AccountDto {
	
	private Long id;
	
	private String branchNumber;
	
	private String accountNumber;
	
	private String accountDigit;
	
	private AccountStatusDto status;
	
	private AccountTypeDto type;
	
	private long currencyId;
	
	private Set<Long> usersId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(String agencyNumber) {
		this.branchNumber = agencyNumber;
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
	
	public AccountStatusDto getStatus() {
		return status;
	}

	public void setStatus(AccountStatusDto status) {
		this.status = status;
	}

	public AccountTypeDto getType() {
		return type;
	}

	public void setType(AccountTypeDto type) {
		this.type = type;
	}

	public long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(long currencyId) {
		this.currencyId = currencyId;
	}

	public Set<Long> getUsersId() {
		return usersId;
	}

	public void setUsersId(Set<Long> usersId) {
		this.usersId = usersId;
	}

	public String toJson() {
		return "{\"id\":null,\"bankAgency\":\"1618\",\"accountNumber\":\"4196658\",\"accountDigit\":\"5\",\"status\":\"ACTIVE\",\"type\":\"CHECKING_ACCOUNT\",\"currencyId\":1,\"usersId\":null}";
	}

}

package com.nextneo.bank.customer.service.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import com.nextneo.bank.integration.dto.AccountDto;
import com.nextneo.bank.integration.dto.enums.AccountStatusDto;
import com.nextneo.bank.integration.dto.enums.AccountTypeDto;
import com.nextneo.bank.models.entity.Account;
import com.nextneo.bank.models.entity.Branch;
import com.nextneo.bank.models.entity.Currency;
import com.nextneo.bank.models.entity.Customer;
import com.nextneo.bank.models.enums.AccountStatus;
import com.nextneo.bank.models.enums.AccountType;

/**
* The AccountMapper class converts Entity to Data Transfer Object
*
* @author  Rafael M Ortiz
* @version 1.0
*/
@Stateless
public class AccountMapper {
	
	public AccountDto accountToAccountDto(Account account) {		
		AccountDto accountDto = null;	
		if(account!=null){	
			accountDto = new AccountDto();
			accountDto.setId(account.getId());
			accountDto.setBranchNumber(account.getAccountBranch().getNumber());
			accountDto.setAccountNumber(account.getAccountNumber());
			accountDto.setAccountDigit(account.getAccountDigit());
			accountDto.setStatus(AccountStatusDto.valueOf(account.getStatus().name()));
			accountDto.setType(AccountTypeDto.valueOf(account.getType().name()));
			if(account.getCurrency()!=null && account.getCurrency().getId()!=null){
				accountDto.setCurrencyId(account.getCurrency().getId());
			}
			if(account.getCustomers()!=null && !account.getCustomers().isEmpty()){
				accountDto.setUsersId(new HashSet<Long>());
				for(Customer customer : account.getCustomers()){
					accountDto.getUsersId().add(customer.getId());
				}
			}
		}
		return accountDto;
	}
	
	public Account accountDtoToAccount(AccountDto accountDto) {		
		Account account = null;	
		if(accountDto!=null){	
			account = new Account();
			account.setId(accountDto.getId());
			account.setAccountBranch(new Branch(null, accountDto.getBranchNumber()));
			account.setAccountNumber(accountDto.getAccountNumber());
			account.setAccountDigit(accountDto.getAccountDigit());
			account.setStatus(AccountStatus.valueOf(accountDto.getStatus().name()));
			account.setType(AccountType.valueOf(accountDto.getType().name()));
			account.setCurrency(new Currency(accountDto.getCurrencyId()));
			if(accountDto.getUsersId()!=null && !accountDto.getUsersId().isEmpty()){
				account.setCustomers(new HashSet<Customer>());
				for(Long customerId : accountDto.getUsersId()){
					Customer customer = new Customer();
					customer.setId(customerId);
					account.getCustomers().add(customer);
					
					Set<Account> accounts = new HashSet<>();
					accounts.add(account);
					customer.setAccounts(accounts);
				}
			}
		}
		return account;
	}
	
	public List<AccountDto> accountListToAccountDtoList(List<Account> accountList){
		List<AccountDto> accountDtoList = null;
		if(accountList!=null && !accountList.isEmpty()){
			accountDtoList = new ArrayList<>();
			for(Account account : accountList){
				AccountDto accountDto = accountToAccountDto(account);
				accountDtoList.add(accountDto);
			}
		}	
		return accountDtoList;	
	}
	
	public List<Account> accountDtoListToAccountList(List<AccountDto> accountDtoList){
		List<Account> accountList = null;
		if(accountDtoList!=null && !accountDtoList.isEmpty()){
			accountList = new ArrayList<>();
			for(AccountDto accountDto : accountDtoList){
				Account account = accountDtoToAccount(accountDto);
				accountList.add(account);
			}
		}	
		return accountList;	
	}

}

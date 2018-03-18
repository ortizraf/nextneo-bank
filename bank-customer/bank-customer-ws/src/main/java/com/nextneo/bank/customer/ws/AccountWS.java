package com.nextneo.bank.customer.ws;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

import com.nextneo.bank.customer.service.AccountService;
import com.nextneo.bank.customer.service.mapper.AccountMapper;
import com.nextneo.bank.integration.dto.AccountDto;
import com.nextneo.bank.models.entity.Account;
import com.nextneo.bank.utils.exception.BusinessException;

@WebService
@Stateless
public class AccountWS {
	
	private static final Logger LOGGER = Logger.getLogger( AccountWS.class.getName() );
	
	@Inject
	private AccountService service;
	
	@Inject
	private AccountMapper accountMapper;
	
	@WebResult(name="accountList")
    @ResponseWrapper(localName="accounts")
	public List<AccountDto> findAll(@WebParam(name="titulo") String titulo) throws BusinessException, Exception {
		LOGGER.info(" AccountWS - findAll "+titulo);
		List<AccountDto> accountListDto = null;
				
		List<Account> accountList = service.findAll();
		accountListDto = accountMapper.accountListToAccountDtoList(accountList);
		
		return accountListDto;
	}
	
	@WebResult(name="account")
	public AccountDto findById(@WebParam(name="id") long id, @WebParam(name="customerId") long customerId) throws BusinessException, Exception {
		LOGGER.info(" AccountWS - findById "+id);
		AccountDto accountDto = null;
				
		Account account = service.findById(id, customerId);
		accountDto = accountMapper.accountToAccountDto(account);
		
		return accountDto;
	}
	
	@WebResult(name="account")
	public List<AccountDto> findByCustomer(@WebParam(name="customerId") long customerId) throws BusinessException, Exception {
		LOGGER.info(" findByUser "+customerId);	
		List<AccountDto> accountListDto = null;
		
		List<Account> accountList = service.findByCustomer(customerId);
		accountListDto = accountMapper.accountListToAccountDtoList(accountList);

		return accountListDto;
	}
	
	@WebResult(name="account")
	public AccountDto addAccount(@WebParam(name="account") AccountDto accountDto) throws BusinessException, Exception {
		LOGGER.info(" AccountWS - addAccount "+accountDto);
		
		Account account = accountMapper.accountDtoToAccount(accountDto);
		
		account = service.addAccount(account);
		
		accountDto = accountMapper.accountToAccountDto(account);

		return accountDto;
	}

}

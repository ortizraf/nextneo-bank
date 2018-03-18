package com.nextneo.bank.customer.ws;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.nextneo.bank.customer.service.AccountMovementService;
import com.nextneo.bank.customer.service.mapper.AccountMovementMapper;
import com.nextneo.bank.integration.dto.AccountMovementDto;
import com.nextneo.bank.models.entity.AccountMovement;
import com.nextneo.bank.models.enums.AccountMovementType;
import com.nextneo.bank.utils.exception.BusinessException;

@WebService
@Stateless
public class AccountMovementWS {
	
	private static final Logger LOGGER = Logger.getLogger( AccountMovementWS.class.getName() );
	
	@Inject
	private AccountMovementService service;
	
	@Inject
	private AccountMovementMapper mapper;
	
	@WebResult(name="accountMovement")
	public AccountMovementDto addAccountMovement(@WebParam(name="accountId") long accountId, @WebParam(name="value") BigDecimal value, @WebParam(name="description") String description, @WebParam(name="type") AccountMovementType type) throws BusinessException, Exception {
		LOGGER.info(" AccountMovementWS - addAccountMovement "+value);
				
		AccountMovementDto accountMovementDto = null;
		
		AccountMovement accountMovement = service.addAccountMovement(accountId, value, description, AccountMovementType.valueOf(type.name()));
		
		accountMovementDto = mapper.accountMovementToAccountMovementDto(accountMovement);
		
		return accountMovementDto;
	}
	
	@WebResult(name="accountMovement")
	public List<AccountMovementDto> findMovementsByAccount(@WebParam(name="accountId") long accountId, @WebParam(name="page") int page, @WebParam(name="maxResult") int maxResult) throws BusinessException, Exception {
		LOGGER.info(" AccountMovementWS - findMovementsByAccount "+accountId);
		
		List<AccountMovementDto> accountMovementDtoList = null;
		
		List<AccountMovement> accountMovementList = service.findMovementsByAccount(accountId, page, maxResult);
		accountMovementDtoList = mapper.accountMovementListToAccountMovementDtoList(accountMovementList);
		
		return accountMovementDtoList;
	}

}

package com.nextneo.bank.customer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.nextneo.bank.customer.repository.AccountMovementRepository;
import com.nextneo.bank.models.entity.AccountMovement;
import com.nextneo.bank.models.enums.AccountMovementType;
import com.nextneo.bank.utils.exception.BusinessException;
import com.nextneo.bank.utils.messages.MessageProperties;

/**
* The Service class does business logic and validation
*
* @author  Rafael M Ortiz
* @version 1.0
*/
@Stateless
public class AccountMovementService {
	
	private static final Logger LOGGER = Logger.getLogger( AccountMovementService.class.getName() );
	
	@Inject
	AccountMovementRepository repository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public AccountMovement addAccountMovement(long accountId, BigDecimal value, String description, AccountMovementType type) throws BusinessException {
		LOGGER.info(" addAccountMovement ");
		
		BigDecimal accountBalance = repository.findAccountBalanceValue(accountId);
		if(accountBalance==null){
			accountBalance = BigDecimal.ZERO;
		}
		if(value==null){
			throw new BusinessException(MessageProperties.getMessage("account_movement_value_is_null"), null);
		}
		if(description==null || "".equals(description)){
			throw new BusinessException(MessageProperties.getMessage("account_movement_description_is_null"), null);
		}
		
		AccountMovement accountMovement = new AccountMovement(accountId, value, description, accountBalance, type);
		accountMovement = repository.addAccountMovement(accountMovement);
		return accountMovement;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<AccountMovement> findMovementsByAccount(long accountId, int page, int maxResult){
		return repository.findMovementsByAccount(accountId, page, maxResult);
	}

}

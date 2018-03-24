package com.nextneo.bank.customer.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.nextneo.bank.customer.repository.AccountRepository;
import com.nextneo.bank.models.entity.Account;
import com.nextneo.bank.models.entity.AccountMovement;
import com.nextneo.bank.models.entity.Branch;
import com.nextneo.bank.utils.errors.Errors;
import com.nextneo.bank.utils.exception.BusinessException;
import com.nextneo.bank.utils.messages.MessageProperties;

/**
* The Service class does business logic and validation
*
* @author  Rafael M Ortiz
* @version 1.0
*/
@Stateless
public class AccountService {
	
	private static final Logger LOGGER = Logger.getLogger( AccountService.class.getName() );
	
	@Inject
	AccountRepository repository;
	
	@Inject
	BranchService agencyService;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Account addAccount(Account account) throws BusinessException, Exception {
		LOGGER.info(" save ");
		consist(account);
		
		return repository.addAccount(account);
	}
	
	private void consist(Account account) throws BusinessException, Exception {
		Errors errors = new Errors();
		if(account==null) {
			errors.addError(MessageProperties.getMessage("account_required"));
			throw new BusinessException(MessageProperties.getMessage("account_required"), errors);
		}
		if(account.getId()!=null){
			errors.addError(MessageProperties.getMessage("use_update_method"));
		}
		if(account.getCustomers()==null || account.getCustomers().isEmpty()){
			errors.addError(MessageProperties.getMessage("account_user_required"));
		}
		Branch branch = agencyService.findByAgencyNumber(account.getAccountBranch().getNumber());
		if(branch==null || branch.getId()==null) {
			errors.addError(MessageProperties.getMessage("agency_invalid"));
		}
		if(errors.hasErrors()) {
			throw new BusinessException(MessageProperties.getMessage("account_add_new_account_error"), errors);
		}
		account.setAccountBranch(branch);
		account.setAccountNumber(repository.findNextAccountNumber(account.getAccountBranch().getId()));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Account updateAccount(Account account) throws BusinessException, Exception {	
		LOGGER.info(" update ");
		if(account.getId()==null){
			throw new BusinessException(MessageProperties.getMessage("account_id_required"), null);
		}
		
		return repository.updateAccount(account);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Account> findAll() throws BusinessException, Exception {
		LOGGER.info(" findAll ");
		
		return repository.findAll();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Account findById(long id, long userId) throws BusinessException, Exception {
		LOGGER.info(" findById ");
		
		Account account = repository.findById(id, userId);
		if (account == null) {
			throw new BusinessException(MessageProperties.getMessage("account_not_found"), null);
		}
		return account;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Account> findLastMovements(long id) throws BusinessException, Exception {	
		List<Account> accounts = repository.findLastMovements(id);
		if(accounts!=null && !accounts.isEmpty()){
			for(Account a : accounts){
				for(AccountMovement m : a.getMovements()){
					System.out.println("" +m.getId()+" "+m.getValue());
				}
			}		
		}
		return accounts;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Account> findByCustomer(long customerId) throws BusinessException, Exception { 
		LOGGER.info(" findByUser ");

		List<Account> accounts = repository.findByCustomer(customerId);
		if(accounts!=null && !accounts.isEmpty()){
			for(Account a : accounts){
				System.out.println("" +a.getId()+" "+a.getAccountBranch().getNumber()); 
			}
		}
		return accounts;
	}

}

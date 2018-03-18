package com.nextneo.bank.customer.service;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.nextneo.bank.customer.repository.AccountRepository;
import com.nextneo.bank.customer.repository.CustomerRepository;
import com.nextneo.bank.customer.repository.LoginRepository;
import com.nextneo.bank.integration.dto.UserDto;
import com.nextneo.bank.integration.wrapper.LoginWrapper;
import com.nextneo.bank.integration.wrapper.UserLoggedWrapper;
import com.nextneo.bank.models.entity.User;
import com.nextneo.bank.utils.crypto.Encryptor;
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
public class LoginService {
	
	private static final Logger LOGGER = Logger.getLogger( LoginService.class.getName() );
	
	@Inject
	LoginRepository repository;
	
	@Inject
	CustomerRepository customerRepository;
	
	@Inject
	AccountRepository accountRepository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User doLogin(LoginWrapper loginWrapper) throws BusinessException{
		Errors errors = new Errors();
		
		LOGGER.info(" doLogin ");
		if(StringUtils.isBlank(loginWrapper.getUserName())){
			errors.addError(MessageProperties.getMessage("login_username_required"));
		}
		if(StringUtils.isBlank(loginWrapper.getPassword())){
			errors.addError(MessageProperties.getMessage("login_password_required"));
		}
		if(errors.hasErrors()) {
			throw new BusinessException("invalid data", errors);
		}
		
		User userFind = new User();
		userFind.setLogin(loginWrapper.getUserName());
		userFind.setPassword(Encryptor.encrypt(loginWrapper.getPassword().trim()));
		
		User user = repository.findByLoginUser(userFind);
		
		return user;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UserLoggedWrapper getInfoAccount(UserDto userDto) throws BusinessException{
		
		Errors errors = new Errors();
				
		Long customerId = customerRepository.findIdByUser(userDto.getId());	
		List<Long> accountsId = accountRepository.findIdByCustomer(customerId);
		if(accountsId == null || accountsId.isEmpty()){
			errors.addError(MessageProperties.getMessage("login_account_not_found"));
		}
		if(errors.hasErrors()) {
			throw new BusinessException("invalid data", errors);
		}
		
		UserLoggedWrapper userLoggedWrapper = new UserLoggedWrapper();
		userLoggedWrapper.setLogin(userDto.getLogin());
		userLoggedWrapper.setPassword(userDto.getPassword());
		userLoggedWrapper.setLastAccess(userDto.getLastAccess());
		userLoggedWrapper.setType(userDto.getType());
		userLoggedWrapper.setGroupRoles(userDto.getGroupRoles());
		userLoggedWrapper.setCustomerId(customerId);
		userLoggedWrapper.setAccountsId(new HashSet<Long>(accountsId));
		
		return userLoggedWrapper;
		
	}
}

package com.nextneo.bank.customer.service;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.nextneo.bank.customer.repository.UserRepository;
import com.nextneo.bank.models.entity.User;
import com.nextneo.bank.models.enums.UserType;
import com.nextneo.bank.utils.exception.BusinessException;
import com.nextneo.bank.utils.messages.MessageProperties;

/**
* The Service class does business logic and validation
*
* @author  Rafael M Ortiz
* @version 1.0
*/
@Stateless
public class UserService {
	
	private static final Logger LOGGER = Logger.getLogger( UserService.class.getName() );
	
	@Inject
	UserRepository repository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User addClient(User user) throws BusinessException, Exception {
		LOGGER.info(" addClient ");	
		if(user.getId()!=null){
			throw new BusinessException(MessageProperties.getMessage("use_update_method"), null);
		}
		user.setType(UserType.CUSTOMER);
		
		return repository.addUser(user);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User updateUser(User user) throws BusinessException, Exception{
		LOGGER.info(" updateUser ");
		if(user.getId()==null){
			throw new BusinessException(MessageProperties.getMessage("user_id_required"), null);
		}
		
		return repository.updateUser(user);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User findById(long id) throws BusinessException, Exception {
		LOGGER.info(" findById ");
		
		User user = repository.findById(id);
		if (user == null || user.getId() == null) {
			throw new BusinessException(MessageProperties.getMessage("user_not_found"), null);
		}
		return user;

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<User> findById(Set<Long> usersId) throws BusinessException, Exception {
		LOGGER.info(" findById ");
		
		List<User> users = repository.findById(usersId);
		if (users == null || users.isEmpty()) {
			throw new BusinessException(MessageProperties.getMessage("user_not_found"), null);
		}
		return users;
	}
	
}

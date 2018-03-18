package com.nextneo.bank.customer.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.nextneo.bank.customer.repository.CustomerRepository;
import com.nextneo.bank.models.entity.Customer;
import com.nextneo.bank.models.entity.GroupRole;
import com.nextneo.bank.models.entity.User;
import com.nextneo.bank.models.enums.UserType;
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
public class CustomerService {
	
	private static final Logger LOGGER = Logger.getLogger( CustomerService.class.getName() );
	
	@Inject
	CustomerRepository repository;
	
	@Inject
	GroupRoleService groupRoleService;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Customer addCustomer(Customer customer) throws BusinessException, Exception {
		LOGGER.info(" addCustomer ");	
		if(customer.getId()!=null){
			throw new BusinessException(MessageProperties.getMessage("use_update_method"), null);
		}
		consist(customer);
		
		customer = repository.addCustomer(customer);
		groupRoleService.updateGroupRoles(customer.getUser().getGroupRoles());
		return customer;
	}
	
	private void consist(Customer customer) throws BusinessException {
		Errors errors = new Errors();
		
		if (customer.getName()==null) {
			errors.addError(MessageProperties.getMessage("customer_name_required"));
		}
		if (customer.getLastName()==null) {
			errors.addError(MessageProperties.getMessage("customer_lastname_required"));
		}
		if (customer.getDocument()==null) {
			errors.addError(MessageProperties.getMessage("customer_document_required"));
		}
		if(errors.hasErrors()) {
			throw new BusinessException("invalid data", errors);
		}
		boolean existsDocument = repository.existsDocument(customer.getDocument(), customer.getId());
		if (existsDocument) {
			errors.addError(MessageProperties.getMessage("document_exists"));
			throw new BusinessException(MessageProperties.getMessage("document_exists"), errors);
		}
		if (customer.getId() == null) {
			User user = new User();
			user.setLogin(customer.getDocument());
			user.setType(UserType.CUSTOMER);
			user.generatePassword();
			
			GroupRole groupRole = groupRoleService.findByName(UserType.CUSTOMER.toString());
			if (groupRole.getUsers() == null) {
				groupRole.setUsers(new HashSet<>());
			}
			groupRole.getUsers().add(user);
			user.setGroupRoles(new HashSet<>());
			user.getGroupRoles().add(groupRole);
			
			customer.setUser(user);
		}
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Customer updateUser(Customer customer) throws BusinessException{
		LOGGER.info(" updateUser ");
		if(customer.getId()==null){
			throw new BusinessException(MessageProperties.getMessage("user_id_required"), null);
		}
		
		return repository.updateCustomer(customer);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Customer findById(long id){
		LOGGER.info(" findById ");
		
		return repository.findById(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Customer> findById(Set<Long> usersId){
		LOGGER.info(" findByAccount ");
		
		return repository.findById(usersId);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Customer> findByAccount(long accountId){
		LOGGER.info(" findByAccount ");
		
		return repository.findByAccount(accountId);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Customer findByUser(long userId){
		LOGGER.info(" findByUser ");
		
		return repository.findByUser(userId);
	}
	
}

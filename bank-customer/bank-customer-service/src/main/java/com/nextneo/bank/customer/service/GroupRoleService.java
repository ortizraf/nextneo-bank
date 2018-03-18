package com.nextneo.bank.customer.service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.nextneo.bank.customer.repository.GroupRoleRepository;
import com.nextneo.bank.models.entity.GroupRole;
import com.nextneo.bank.utils.exception.BusinessException;

/**
* The Service class does business logic and validation
*
* @author  Rafael M Ortiz
* @version 1.0
*/
@Stateless
public class GroupRoleService {
	
	private static final Logger LOGGER = Logger.getLogger( GroupRoleService.class.getName() );
	
	@Inject
	GroupRoleRepository repository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GroupRole addGroupRole(GroupRole groupRole) throws BusinessException, Exception {
		LOGGER.info(" addGroupRole ");
		
		return repository.addGroupRole(groupRole);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GroupRole updateGroupRole(GroupRole groupRole) throws BusinessException, Exception {
		LOGGER.info(" updateGroupRole ");
		
		return repository.updateGroupRole(groupRole);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Set<GroupRole> updateGroupRoles(Set<GroupRole> groupRoles) throws BusinessException, Exception {
		LOGGER.info(" updateGroupRoles ");
		Set<GroupRole> groupRolesPersist = new HashSet<>();
		
		for (GroupRole groupRole : groupRoles) {
			groupRolesPersist.add(repository.updateGroupRole(groupRole));
		}
		return groupRolesPersist;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GroupRole findByName(String name){
		LOGGER.info(" findByName ");
		
		return repository.findByName(name);
	}
	
}

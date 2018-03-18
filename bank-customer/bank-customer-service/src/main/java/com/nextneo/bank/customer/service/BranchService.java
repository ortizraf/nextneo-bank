package com.nextneo.bank.customer.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.nextneo.bank.customer.repository.BranchRepository;
import com.nextneo.bank.models.entity.Branch;

/**
* The Service class does business logic and validation
*
* @author  Rafael M Ortiz
* @version 1.0
*/
@Stateless
public class BranchService {
	
	private static final Logger LOGGER = Logger.getLogger( BranchService.class.getName() );
	
	@Inject
	BranchRepository repository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Branch findByAgencyNumber(String agencyNumber){ 	
		LOGGER.info(" findById ");
		
		return repository.findByBranchNumber(agencyNumber);
	}

}

package com.nextneo.bank.customer.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.nextneo.bank.models.entity.Branch;

@Stateless
public class BranchRepository {
	
	@PersistenceContext
    private EntityManager manager;
	
	public Branch findByBranchNumber(String number) {
		
		TypedQuery<Branch> query = manager.createQuery("SELECT a from Branch a where a.number = :pAgencyNumber ", Branch.class);
		query.setParameter("pAgencyNumber", number);
		
		Branch agency = null;
		try {
			agency = query.setMaxResults(1).getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}	
		return agency;
	}

}

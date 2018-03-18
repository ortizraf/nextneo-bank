package com.nextneo.bank.customer.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.nextneo.bank.models.entity.AccountMovement;
import com.nextneo.bank.utils.messages.MessageProperties;

@Stateless
public class AccountMovementRepository {
	
	public static int QUANTITY_RESULTS = 100;
	
	@PersistenceContext
    private EntityManager manager;
	
	public BigDecimal findAccountBalanceValue(long accountId){
		
		TypedQuery<BigDecimal> query = manager.createQuery("SELECT m.accountBalance from AccountMovement m where m.account.id = :pAccountId order by m.id desc ", BigDecimal.class);
		query.setParameter("pAccountId", accountId);
		
		BigDecimal accountBalance = null;
		try {
			accountBalance = query.setMaxResults(1).getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}	
		return accountBalance;
	}

	public AccountMovement addAccountMovement(AccountMovement accountMovement){
		manager.persist(accountMovement);
		return accountMovement;
	}
	
	public List<AccountMovement> findMovementsByAccount(long accountId, int page, int maxResult){
		if(page<=0) {
			throw new RuntimeErrorException(null, MessageProperties.getMessage("account_movement_page_is_null"));
		}
		if(maxResult<=0){
			maxResult = QUANTITY_RESULTS;
		} else if (maxResult > QUANTITY_RESULTS) {
			throw new RuntimeErrorException(null, MessageProperties.getMessage("account_movement_invalid_quantity")+" "+QUANTITY_RESULTS);
		}	
		int firstResult = (page * maxResult) - maxResult;
		
		TypedQuery<AccountMovement> query = manager.createQuery("SELECT m from AccountMovement m where m.account.id = :pAccountId order by m.id desc ", AccountMovement.class);
		query.setParameter("pAccountId", accountId);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);

		List<AccountMovement> accountMovementList = query.getResultList();

		return accountMovementList;
	}

}

package com.nextneo.bank.customer.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import com.nextneo.bank.models.entity.Account;

@Stateless
public class AccountRepository {
	
	public static int DEFAULT_MAX_RESULTS = 500;
	public static String DEFAULT_INIT_ACCOUNT_NUMBER = "00999";
	
	@PersistenceContext
    private EntityManager manager;
	
	public Account addAccount(Account account){
		manager.persist(account);
		return account;
	}
	
    public Account updateAccount(Account account) {
        manager.merge(account);
        return account;
    }
	
	public List<Account> findAll(){	
		
		TypedQuery<Account> query = manager.createQuery("SELECT new Account(a.bankAgency, a.accountNumber, a.accountDigit, a.status, a.type, c) from Account a join a.currency c order by a.id ", Account.class);
		
		List<Account> accountList = query.getResultList();
		query.setMaxResults(DEFAULT_MAX_RESULTS);
		
		return accountList;
	}
	
	public List<Account> findLastMovements(long id){
		
		TypedQuery<Account> query = manager.createQuery("SELECT distinct a from Account a left join fetch a.movements where a.id = :pId order by a.id desc ", Account.class);
		query.setParameter("pId", id);
		query.setMaxResults(DEFAULT_MAX_RESULTS);

		List<Account> accountList = query.getResultList();
		
		return accountList;
	}
	
	public List<Account> findByCustomer(long customerId){
		
		TypedQuery<Account>  query = manager.createQuery("SELECT distinct a from Account a left join fetch a.customers u where u.id = :pId ", Account.class);
		query.setParameter("pId", customerId);
		query.setMaxResults(DEFAULT_MAX_RESULTS);

		List<Account> accountList = query.getResultList();

		return accountList;
	}
	
	public List<Long> findIdByCustomer(long customerId){
		
		TypedQuery<Long> query = manager.createQuery("SELECT distinct a.id from Account a left join a.customers u where u.id = :pId ", Long.class);
		query.setParameter("pId", customerId);
		query.setMaxResults(DEFAULT_MAX_RESULTS);

		List<Long> accountList = query.getResultList();

		return accountList;
	}

	public Account findById(long id, long idCustomer) {
		
		TypedQuery<Account> query = manager.createQuery("SELECT new Account(a.accountBranch, a.accountNumber, a.accountDigit, a.status, a.type, c) from Account a join a.customers u join a.currency c where a.id = :pId and u.id = :pCustomerId order by a.id ", Account.class);
		query.setParameter("pId", id);
		query.setParameter("pCustomerId", idCustomer);
		
		Account account = null;
		try {
			account = query.setMaxResults(1).getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}	
		return account;
	}
	
	public String findNextAccountNumber(Long branchId) {
		TypedQuery<String> query = manager.createQuery("SELECT max(a.accountNumber) from Account a where accountBranch.id = :pAccountBranch", String.class);
		query.setParameter("pAccountBranch", branchId);

		String accountNumber = null;
		try {
			accountNumber = query.setMaxResults(1).getSingleResult();
			if(accountNumber==null) {
				accountNumber = DEFAULT_INIT_ACCOUNT_NUMBER;
			}
			accountNumber = StringUtils.leftPad(String.valueOf(Integer.parseInt(accountNumber) + 1), 6, "0");
		} catch (NoResultException nre) {
			/* NoResultException */
		}
		return accountNumber;
	}

}

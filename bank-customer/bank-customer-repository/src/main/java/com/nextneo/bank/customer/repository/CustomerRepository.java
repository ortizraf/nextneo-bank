package com.nextneo.bank.customer.repository;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.nextneo.bank.models.entity.Customer;

@Stateless
public class CustomerRepository {
	
	public static int DEFAULT_MAX_RESULTS = 500;

	@PersistenceContext
	private EntityManager manager;
	
	public Customer addCustomer(Customer customer) {
		manager.persist(customer);
		return customer;
	}
	
    public Customer updateCustomer(Customer customer) {
        manager.merge(customer);
        return customer;
    }
	
	public Customer findById(long id){
		
		TypedQuery<Customer>  query = manager.createQuery("SELECT new Customer(u) from Customer u where u.id = :pId ", Customer.class);
		query.setParameter("pId", id);
			
		Customer customer = null;
		try {
			customer = query.getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}

		return customer;
	}
	
	public List<Customer> findById(Set<Long> ids){
		
		TypedQuery<Customer>  query = manager.createQuery("SELECT new Customer(u) from Customer u where u.id in :pIds ", Customer.class);
		query.setParameter("pIds", ids);
			
		List<Customer> customerList = query.getResultList();
		return customerList;
	}
	
	public List<Customer> findByAccount(long idAccount){
		
		TypedQuery<Customer> query = manager.createQuery("SELECT distinct new Customer(u) from Customer u left join u.accounts a where a.id = :pIdAccount ", Customer.class);
		query.setParameter("pIdAccount", idAccount);
		query.setMaxResults(DEFAULT_MAX_RESULTS);
	
		List<Customer> customerList = query.getResultList();

		return customerList;
	}
	
	public Customer findByUser(long idUser){
		
		TypedQuery<Customer> query = manager.createQuery("SELECT distinct new Customer(c) from Customer c where c.user.id = :pIdUser ", Customer.class);
		query.setParameter("pIdUser", idUser);
	
		Customer customer = null;
		try {
			customer = query.getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}
		
		return customer;
	}
	
	public Long findIdByUser(long idUser){
		
		TypedQuery<Long> query = manager.createQuery("SELECT distinct c.id from Customer c where c.user.id = :pIdUser ", Long.class);
		query.setParameter("pIdUser", idUser);
	
		Long customerId = null;
		try {
			customerId = query.getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}
		
		return customerId;
	}

	public boolean existsDocument(String document, Long id) {
		
		StringBuilder queryS = new StringBuilder();
		queryS.append("SELECT count(c) from Customer c ");
		queryS.append("WHERE");
		queryS.append(" c.document = :pDocument");
		if (id != null && id > 0) {
			queryS.append(" and c.id = :pId");
			
		}
		
		TypedQuery<Long>  query = manager.createQuery(queryS.toString(), Long.class);
		query.setParameter("pDocument", document);
		if (id != null && id > 0) {
			query.setParameter("pId", id);
		}
		
		long count = query.getSingleResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

}

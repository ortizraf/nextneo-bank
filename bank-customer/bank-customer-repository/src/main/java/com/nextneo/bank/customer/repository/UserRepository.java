package com.nextneo.bank.customer.repository;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.nextneo.bank.models.entity.User;

@Stateless
public class UserRepository {
	
	public static int DEFAULT_MAX_RESULTS = 500;

	@PersistenceContext
	private EntityManager manager;
	
	public User addUser(User user){	
		manager.persist(user);	
		return user;
	}
	
    public User updateUser(User user) {
        manager.merge(user);
        return user;
    }
	
	public User findById(long id){
		
		TypedQuery<User>  query = manager.createQuery("SELECT new User(u) from User u where u.id = :pId ", User.class);
		query.setParameter("pId", id);
			
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}

		return user;
	}
	
	public List<User> findById(Set<Long> ids){
		
		TypedQuery<User>  query = manager.createQuery("SELECT new User(u) from User u where u.id in :pIds ", User.class);
		query.setParameter("pIds", ids);
			
		List<User> userList = query.getResultList();
		return userList;
	}

}

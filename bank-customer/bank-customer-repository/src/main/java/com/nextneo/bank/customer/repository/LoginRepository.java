package com.nextneo.bank.customer.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.nextneo.bank.models.entity.User;

@Stateless
public class LoginRepository {
	
	public static int DEFAULT_MAX_RESULTS = 500;

	@PersistenceContext
	private EntityManager manager;
	
	public User findByLoginUser(User userFind){
		
		TypedQuery<User> query = manager.createQuery("SELECT new User(u) from User u where u.login = :pLogin and u.password = :pPassword", User.class);
		query.setParameter("pLogin", userFind.getLogin());
		query.setParameter("pPassword", userFind.getPassword());

		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}

		return user;
	}

}

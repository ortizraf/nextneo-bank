package com.nextneo.bank.customer.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.nextneo.bank.models.entity.GroupRole;

@Stateless
public class GroupRoleRepository {
	
	public static int DEFAULT_MAX_RESULTS = 500;

	@PersistenceContext
	private EntityManager manager;
	
	public GroupRole addGroupRole(GroupRole groupRole) {
		manager.persist(groupRole);
		return groupRole;
	}
	
    public GroupRole updateGroupRole(GroupRole groupRole) {
        manager.merge(groupRole);
        return groupRole;
    }
	
	public GroupRole findByName(String name){
		
		TypedQuery<GroupRole>  query = manager.createQuery("SELECT new GroupRole(g) from GroupRole g where g.name = :pName ", GroupRole.class);
		query.setParameter("pName", name);
			
		GroupRole groupRole = null;
		try {
			groupRole = query.getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}

		return groupRole;
	}

}

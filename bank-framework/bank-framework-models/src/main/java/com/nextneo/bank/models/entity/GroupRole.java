package com.nextneo.bank.models.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.nextneo.bank.models.BaseModel;

@Entity
@Table(name = "group_role")
public class GroupRole extends BaseModel {
	
	@NotNull
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "group_role_user", joinColumns = { @JoinColumn(name = "group_role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public GroupRole() {
		
	}
	
	public GroupRole(Long id) {
		this.setId(id);
	}
	
	public GroupRole(GroupRole groupRole) {
		this.setId(groupRole.getId());
		this.setCreateDate(groupRole.getCreateDate());
		this.setUpdateDate(groupRole.getUpdateDate());
		this.name = groupRole.getName();
		this.users = groupRole.getUsers();
	}
	
}

package com.nextneo.bank.models.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.nextneo.bank.models.BaseModel;

@Entity
@Table(name = "customer", indexes = { @Index(name="IDX_CUSTOMER", columnList="document") }, uniqueConstraints = @UniqueConstraint(columnNames = { "document" }))
public class Customer extends BaseModel {
	
	@NotNull
	private String name;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String document;
	
	@ManyToMany(mappedBy="customers")
    private Set<Account> accounts;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Customer() {
		super();
	}
	
	public Customer(Customer customer) {
		this.setId(customer.getId());
		this.name = customer.getName();
		this.lastName = customer.getLastName();
		this.document = customer.getDocument();
		this.user = customer.getUser();
	}
	
}

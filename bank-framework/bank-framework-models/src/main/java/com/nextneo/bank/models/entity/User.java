package com.nextneo.bank.models.entity;

import java.util.Calendar;
import java.util.Random;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.nextneo.bank.models.BaseModel;
import com.nextneo.bank.models.enums.UserType;
import com.nextneo.bank.utils.crypto.Encryptor;

@Entity
@Table(name = "user", indexes = { @Index(name="IDX_USER", columnList="login,type") }, uniqueConstraints = @UniqueConstraint(columnNames = { "login", "type" }))
public class User extends BaseModel{
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_Access")
	private Calendar lastAccess;
	
	private String login;
	
	@NotNull 
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private UserType type;
	
	private String password;
	
	@ManyToMany(mappedBy="users", fetch=FetchType.EAGER)
    private Set<GroupRole> groupRoles;

	public Calendar getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Calendar lastAccess) {
		this.lastAccess = lastAccess;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public Set<GroupRole> getGroupRoles() {
		return groupRoles;
	}

	public void setGroupRoles(Set<GroupRole> groupRoles) {
		this.groupRoles = groupRoles;
	}

	public User() {

	}
	
	public User(User user) {
		this.setId(user.getId());
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.type = user.getType();
		this.groupRoles = user.getGroupRoles();
	}
	
	public void generatePassword() {
		Random rnd = new Random();
		Integer randomPassword = 100000 + rnd.nextInt(900000);
		System.out.println("login: "+getLogin()+" | password: "+randomPassword);
		this.password = Encryptor.encrypt(randomPassword.toString());
	}

}

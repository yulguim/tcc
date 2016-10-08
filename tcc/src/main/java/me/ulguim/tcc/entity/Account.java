package me.ulguim.tcc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import in.k2s.sdk.jpa.entity.BaseEntity;

@Entity
@Table(name="account")
public class Account extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String email;
	
	private String password;
	
	public Account() {
		
	}
	
	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

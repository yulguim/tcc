package me.ulguim.tcc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
	
	private String avatar;
	
	private String username;
	
	private String name;

	private String lastname;

	private String email;
	
	private String password;

	private String chave;
	private Timestamp insertTime;
	private Timestamp updateTime;
	private Timestamp insertBy;
	private Timestamp updateBy;

	public Account() {
		
	}

	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(Timestamp insertBy) {
		this.insertBy = insertBy;
	}

	public Timestamp getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Timestamp updateBy) {
		this.updateBy = updateBy;
	}
}

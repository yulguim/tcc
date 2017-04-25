package me.ulguim.tcc.entity;

import in.k2s.sdk.jpa.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yulle on 24/04/17.
 */
@Entity
@Table(name="account_projeto")
public class AccountProjeto  extends BaseEntity implements Serializable {

	@Id
	private Long id;

	private String chave;

	@ManyToOne
	@JoinColumn(name="fk_account")
	private Account account;

	@ManyToOne
	@JoinColumn(name="fk_projeto")
	private Projeto projeto;

	@Column(name="insert_by")
	private Long insertBy;

	@Column(name="insert_time")
	private Timestamp insertTime;

	@Column(name="update_by")
	private Long updateBy;

	@Column(name="update_time")
	private Timestamp updateTime;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Long getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(Long insertBy) {
		this.insertBy = insertBy;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}

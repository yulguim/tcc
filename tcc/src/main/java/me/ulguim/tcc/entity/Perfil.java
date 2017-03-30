package me.ulguim.tcc.entity;

import in.k2s.sdk.jpa.entity.BaseEntity;
import me.ulguim.tcc.bean.ExtraParamsBean;
import me.ulguim.tcc.bean.HabilidadeBean;
import me.ulguim.tcc.bean.SocialNetwork;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.converter.ExtraParamsConverter;
import me.ulguim.tcc.entity.converter.HabilidadesConverter;
import me.ulguim.tcc.entity.location.Estado;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by yulle on 27/01/17.
 */

@Entity
@Table(name="profile")
public class Perfil extends BaseEntity implements Serializable {

	@Id
	private Long id;

	@OneToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "ocupacao_id")
	private Ocupacao ocupacao;

	@Column(name = "habilidades")
	@Convert(converter = HabilidadesConverter.class)
	private List<HabilidadeBean> habilidadeList;

	//	private List<SocialNetwork> socialNetworkList;

	private String chave;
	@Column(name = "insert_time")
	private Timestamp insertTime;
	@Column(name = "update_time")
	private Timestamp updateTime;
	@Column(name = "insert_by")
	private Long insertBy;
	@Column(name = "update_by")
	private Long updateBy;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(Long insertBy) {
		this.insertBy = insertBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public List<HabilidadeBean> getHabilidadeList() {
		return habilidadeList;
	}

	public void setHabilidadeList(List<HabilidadeBean> habilidadeList) {
		this.habilidadeList = habilidadeList;
	}

	public Ocupacao getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(Ocupacao ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getLabel() {
		return this.account.getLabel();
	}
}

package me.ulguim.tcc.entity;

import in.k2s.sdk.jpa.entity.BaseEntity;
import me.ulguim.tcc.bean.MensagemBean;
import me.ulguim.tcc.entity.converter.HabilidadesConverter;
import me.ulguim.tcc.entity.converter.MensagemConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulle on 17/01/17.
 */
@Entity
@Table(name = "chat")
public class Chat extends BaseEntity implements Serializable {

	@Id
	private Long id;

	private Long user1;

	private Long user2;

	@Column(name = "mensagens")
	@Convert(converter = MensagemConverter.class)
	private List<MensagemBean> mensagens = new ArrayList<>();

	private String chave;
	@Column(name = "insert_time")
	private Timestamp insertTime;
	@Column(name = "update_time")
	private Timestamp updateTime;
	@Column(name = "insert_by")
	private Long insertBy;
	@Column(name = "update_by")
	private Long updateBy;

	public Chat() {

	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser1() {
		return user1;
	}

	public void setUser1(Long user1) {
		this.user1 = user1;
	}

	public Long getUser2() {
		return user2;
	}

	public void setUser2(Long user2) {
		this.user2 = user2;
	}

	public List<MensagemBean> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<MensagemBean> mensagens) {
		this.mensagens = mensagens;
	}

	public void addMensagem(MensagemBean bean) {this.mensagens.add(bean); }

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
}

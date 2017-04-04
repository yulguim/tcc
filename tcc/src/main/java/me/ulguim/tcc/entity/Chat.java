package me.ulguim.tcc.entity;

import in.k2s.sdk.jpa.entity.BaseEntity;
import me.ulguim.tcc.bean.MensagemBean;
import me.ulguim.tcc.entity.converter.HabilidadesConverter;

import javax.persistence.*;
import java.io.Serializable;
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
	@Convert(converter = HabilidadesConverter.class)
	private List<MensagemBean> mensagens = new ArrayList<>();

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




}

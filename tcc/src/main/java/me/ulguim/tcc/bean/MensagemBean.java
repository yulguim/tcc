package me.ulguim.tcc.bean;

import in.k2s.sdk.core.bean.BaseBean;
import in.k2s.sdk.util.data.DataUtil;

import java.sql.Timestamp;

/**
 * Created by yulle on 07/03/17.
 */
public class MensagemBean extends BaseBean {

	private Long id;

	private Long userId;

	private String mensagem;

	private String data;

	public MensagemBean() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = DataUtil.format(data, "dd/MM/yyyy HH:mm:ss");
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}

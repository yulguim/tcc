package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;

@View
public class MensagemView extends BaseView {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long chatId;

	private Long userId;

	private String userKey;

	private String mensagem;

	private String data;

	public MensagemView() {

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

	public void setData(String data) {
		this.data = data;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
}

package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;

import java.util.ArrayList;
import java.util.List;

@View
public class ChatView extends BaseView {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long user1;

	private String user1Label;

	private Long user2;

	private String user2Label;

	private List<MensagemView> mensagens = new ArrayList<>();

	public ChatView() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser1() {
		return user1;
	}

	public void setUser1(Long user1) {
		this.user1 = user1;
	}

	public String getUser1Label() {
		return user1Label;
	}

	public void setUser1Label(String user1Label) {
		this.user1Label = user1Label;
	}

	public Long getUser2() {
		return user2;
	}

	public void setUser2(Long user2) {
		this.user2 = user2;
	}

	public String getUser2Label() {
		return user2Label;
	}

	public void setUser2Label(String user2Label) {
		this.user2Label = user2Label;
	}

	public List<MensagemView> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<MensagemView> mensagens) {
		this.mensagens = mensagens;
	}
}

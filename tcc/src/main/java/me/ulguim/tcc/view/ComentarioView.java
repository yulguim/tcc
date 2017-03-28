package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;

@View
public class ComentarioView extends BaseView {
	private static final long serialVersionUID = 1L;

	private String postKey;

	private String comentario;

	private Long authorId;

	private String authorLabel;

	private String insertTime;

	private int index;

	public ComentarioView() {
		
	}

	public String getPostKey() {
		return postKey;
	}

	public void setPostKey(String postKey) {
		this.postKey = postKey;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorLabel() {
		return authorLabel;
	}

	public void setAuthorLabel(String authorLabel) {
		this.authorLabel = authorLabel;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
}

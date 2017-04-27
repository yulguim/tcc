package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;

import java.util.ArrayList;
import java.util.List;

@View
public class PostView extends BaseView {
	private static final long serialVersionUID = 1L;

	private String projetoKey;

	private String key;

	private String post;

	private Long authorId;

	private String authorKey;

	private String authorAvatar;

	private String authorLabel;

	private String insertTime;

	private List<ComentarioView> commentList = new ArrayList<>();

	public PostView() {
		
	}

	public String getProjetoKey() {
		return projetoKey;
	}

	public void setProjetoKey(String projetoKey) {
		this.projetoKey = projetoKey;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
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

	public List<ComentarioView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<ComentarioView> commentList) {
		this.commentList = commentList;
	}

	public String getAuthorAvatar() {
		return authorAvatar;
	}

	public void setAuthorAvatar(String authorAvatar) {
		this.authorAvatar = authorAvatar;
	}

	public String getAuthorKey() {
		return authorKey;
	}

	public void setAuthorKey(String authorKey) {
		this.authorKey = authorKey;
	}
}

package me.ulguim.tcc.entity;

import in.k2s.sdk.jpa.entity.BaseEntity;
import me.ulguim.tcc.bean.ComentarioBean;
import me.ulguim.tcc.entity.converter.ComentarioConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by yulle on 17/01/17.
 */
public class Post extends BaseEntity implements Serializable {

	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_author")
	private Account author;

	private String post;

	@Convert(converter = ComentarioConverter.class)
	private List<ComentarioBean> comentarioList;

	private String chave;
	@Column(name = "insert_time")
	private Timestamp insertTime;
	@Column(name = "update_time")
	private Timestamp updateTime;
	@Column(name = "insert_by")
	private Timestamp insertBy;
	@Column(name = "update_by")
	private Timestamp updateBy;

	public Post() {

	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Account getAuthor() {
		return author;
	}

	public void setAuthor(Account author) {
		this.author = author;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public List<ComentarioBean> getComentarioList() {
		return comentarioList;
	}

	public void setComentarioList(List<ComentarioBean> comentarioList) {
		this.comentarioList = comentarioList;
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

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
@Entity
@Table(name = "post")
public class Post extends BaseEntity implements Serializable {

	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_author")
	private Account author;

	private String post;

	@Column(name="comentarios", columnDefinition="TEXT")
	@Convert(converter = ComentarioConverter.class)
	private List<ComentarioBean> comentarioList;

	private String chave;
	@Column(name = "insert_time")
	private Timestamp insertTime;
	@Column(name = "update_time")
	private Timestamp updateTime;
	@Column(name = "insert_by")
	private Long insertBy;
	@Column(name = "update_by")
	private Long updateBy;

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

	public ComentarioBean getComentarioById(Long id) {
			return this.comentarioList.stream()
					.filter(c -> c.getId().equals(id))
					.findAny().orElseGet(null);
	}

	public boolean removeComentarioById(Long id) {
		return this.comentarioList.removeIf(c -> c.getId().equals(id));
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

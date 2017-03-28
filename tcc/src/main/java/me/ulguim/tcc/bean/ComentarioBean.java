package me.ulguim.tcc.bean;

import in.k2s.sdk.core.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulle on 07/03/17.
 */
public class ComentarioBean extends BaseBean {

	private Long idUsuario;

	private String labelUsuario;

	private String comentario;

	private int likes;

	private String insertTime;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getLabelUsuario() {
		return labelUsuario;
	}

	public void setLabelUsuario(String labelUsuario) {
		this.labelUsuario = labelUsuario;
	}
}

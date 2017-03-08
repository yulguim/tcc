package me.ulguim.tcc.entity;

import in.k2s.sdk.jpa.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by yulle on 17/01/17.
 */
public class Post extends BaseEntity implements Serializable {

	private Long id;

	public Post() {

	}

	@Override
	public Long getId() {
		return null;
	}

	@Override
	public void setId(Long id) {

	}
}

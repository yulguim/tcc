package me.ulguim.tcc.entity;

import in.k2s.sdk.jpa.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by yulle on 17/01/17.
 */
@Entity
@Table(name = "contato")
public class Contato extends BaseEntity implements Serializable {

	@Id
	private Long id;

	public Contato() {

	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}

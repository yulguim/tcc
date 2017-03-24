package me.ulguim.tcc.entity.other;

import in.k2s.sdk.jpa.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yulle on 24/03/17.
 */
@Entity
@Table(name="habilidade")
public class Habilidade extends BaseEntity {

	@Id
	public Long id;

	public String label;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}

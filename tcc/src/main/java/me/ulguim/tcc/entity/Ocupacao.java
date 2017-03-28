package me.ulguim.tcc.entity;

import in.k2s.sdk.jpa.entity.BaseEntity;
import me.ulguim.tcc.entity.location.Cidade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yulle on 17/01/17.
 */
@Entity
@Table(name = "ocupacao")
public class Ocupacao extends BaseEntity implements Serializable {

	@Id
	private Long id;

	private String label;

	@OneToMany(mappedBy = "ocupacao", fetch = FetchType.LAZY)
	private List<Perfil> perfilList;

	public Ocupacao() {

	}

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

	public List<Perfil> getPerfilList() {
		return perfilList;
	}

	public void setPerfilList(List<Perfil> perfilList) {
		this.perfilList = perfilList;
	}
}

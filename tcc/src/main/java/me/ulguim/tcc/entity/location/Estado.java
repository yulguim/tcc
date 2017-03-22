package me.ulguim.tcc.entity.location;

import in.k2s.sdk.jpa.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yulle on 22/03/17.
 */
@Entity
@Table(name = "estados")
public class Estado extends BaseEntity implements Serializable {

	@Id
	private Long id;

	private String nome;

	private String sigla;

	@OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
	private transient List<Cidade> cidadeList;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		//this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

	public List<Cidade> getCidadeList() {
		return cidadeList;
	}
}

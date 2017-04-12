package me.ulguim.tcc.entity.location;

import in.k2s.sdk.jpa.entity.BaseEntity;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.Post;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yulle on 22/03/17.
 */
@Entity
@Table(name = "cidades")
public class Cidade extends BaseEntity implements Serializable {

	@Id
	private Long id;

	private String nome;

	@Column(name = "codigo_ibge")
	private String codigoIBGE;

	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@Column(name = "populacao_2010")
	private Long populacao;

	@Column(name = "densidade_demo")
	private Double densidadeDemografica;

	private String gentilico;

	private Double area;

	@OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
	private List<Perfil> perfilList;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {

	}

	public String getNome() {
		return nome;
	}

	public String getCodigoIBGE() {
		return codigoIBGE;
	}

	public Estado getEstado() {
		return estado;
	}

	public Long getPopulacao() {
		return populacao;
	}

	public Double getDensidadeDemografica() {
		return densidadeDemografica;
	}

	public String getGentilico() {
		return gentilico;
	}

	public Double getArea() {
		return area;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCodigoIBGE(String codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setPopulacao(Long populacao) {
		this.populacao = populacao;
	}

	public void setDensidadeDemografica(Double densidadeDemografica) {
		this.densidadeDemografica = densidadeDemografica;
	}

	public void setGentilico(String gentilico) {
		this.gentilico = gentilico;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public List<Perfil> getPerfilList() {
		return perfilList;
	}

	public void setPerfilList(List<Perfil> perfilList) {
		this.perfilList = perfilList;
	}
}

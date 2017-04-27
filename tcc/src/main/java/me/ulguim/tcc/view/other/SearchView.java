package me.ulguim.tcc.view.other;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.PerfilView;
import me.ulguim.tcc.view.ProjetoView;

import java.util.List;

/**
 * Created by yulle on 06/04/17.
 */
@View
public class SearchView extends BaseView {

	private String search;

	private Boolean perfil;

	private Boolean projetos;

	private List<ContatoView> perfilResults;

	private List<ProjetoView> projetoResults;

	public Boolean getPerfil() {
		return perfil;
	}

	public void setPerfil(Boolean perfil) {
		this.perfil = perfil;
	}

	public Boolean getProjetos() {
		return projetos;
	}

	public void setProjetos(Boolean projetos) {
		this.projetos = projetos;
	}

	public List<ContatoView> getPerfilResults() {
		return perfilResults;
	}

	public void setPerfilResults(List<ContatoView> perfilResults) {
		this.perfilResults = perfilResults;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<ProjetoView> getProjetoResults() {
		return projetoResults;
	}

	public void setProjetoResults(List<ProjetoView> projetoResults) {
		this.projetoResults = projetoResults;
	}
}

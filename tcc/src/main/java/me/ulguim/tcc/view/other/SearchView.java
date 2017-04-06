package me.ulguim.tcc.view.other;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;
import me.ulguim.tcc.view.PerfilView;

import java.util.List;

/**
 * Created by yulle on 06/04/17.
 */
@View
public class SearchView extends BaseView {

	private Boolean perfil;

	private Boolean projetos;

	private List<PerfilView> perfilResults;

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

	public List<PerfilView> getPerfilResults() {
		return perfilResults;
	}

	public void setPerfilResults(List<PerfilView> perfilResults) {
		this.perfilResults = perfilResults;
	}
}

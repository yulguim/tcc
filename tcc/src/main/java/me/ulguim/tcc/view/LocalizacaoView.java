package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;

/**
 * Created by yulle on 24/03/17.
 */
@View
public class LocalizacaoView extends BaseView {

	private Long id;

	private String label;

	public LocalizacaoView() {

	}

	public LocalizacaoView(Long id, String label) {
		this.id = id;
		this.label = label;
	}

	public Long getId() {
		return id;
	}

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

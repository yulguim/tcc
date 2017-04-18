package me.ulguim.tcc.bean;

import in.k2s.sdk.core.bean.BaseBean;

/**
 * Created by yulle on 07/03/17.
 */
public class TagBean extends BaseBean {

	private Long id;

	private String label;

	public TagBean() {

	}

	public TagBean(Long id, String label) {
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

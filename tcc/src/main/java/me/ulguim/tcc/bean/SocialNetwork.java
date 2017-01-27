package me.ulguim.tcc.bean;

import in.k2s.sdk.core.bean.BaseBean;

/**
 * Created by yulle on 27/01/17.
 */
public class SocialNetwork extends BaseBean {

	private String label;

	private String url;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

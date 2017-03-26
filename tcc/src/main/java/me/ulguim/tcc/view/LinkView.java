package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;
import me.ulguim.tcc.enumeration.LinkSiteEnum;

/**
 * Created by yulle on 24/03/17.
 */
@View
public class LinkView extends BaseView {

	private LinkSiteEnum site;

	private String url;

	public LinkView() {

	}

	public LinkSiteEnum getSite() {
		return site;
	}

	public void setSite(LinkSiteEnum site) {
		this.site = site;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

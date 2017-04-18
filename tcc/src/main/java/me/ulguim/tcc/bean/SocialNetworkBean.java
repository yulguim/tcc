package me.ulguim.tcc.bean;

import in.k2s.sdk.core.bean.BaseBean;

/**
 * Created by yulle on 27/01/17.
 */
public class SocialNetworkBean extends BaseBean {

	private Label label;

	private String description;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public enum Label {
		FACEBOOK("Facebook"), LINKED_IN("LinkedIn"), TWITTER("Twitter");

		private final String name;

		Label(String s) {
			name = s;
		}

		public String toString() {
			return this.name;
		}
	}

}

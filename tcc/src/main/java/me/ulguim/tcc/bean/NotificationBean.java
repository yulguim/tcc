package me.ulguim.tcc.bean;

import in.k2s.sdk.core.bean.BaseBean;

/**
 * Created by yulle on 07/03/17.
 */
public class NotificationBean extends BaseBean {

	private Long id;

	private String label;

	private String description;

	private String url;

	private boolean read = false;

	public NotificationBean() {

	}

	public NotificationBean(String label, String description) {
		this.label = label;
		this.description = description;
	}

	public NotificationBean(String label, String description, String url) {
		this.label = label;
		this.description = description;
		this.url = url;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public static NotificationBean createNotification(String label, String description) {
		return new NotificationBean(label, description);
	}

	public static NotificationBean createNotification(String label, String description, String url) {
		return new NotificationBean(label, description, url);
	}
}

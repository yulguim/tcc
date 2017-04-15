package me.ulguim.tcc.bean;

import in.k2s.sdk.core.bean.BaseBean;

/**
 * Created by yulle on 07/03/17.
 */
public class NotificationBean extends BaseBean {

	private Long id;

	private Label label;

	private String description;

	private String url;

	private boolean read = false;

	public NotificationBean() {

	}

	private NotificationBean(Label label, String description) {
		this.label = label;
		this.description = description;
	}

	private NotificationBean(Label label, String description, String url) {
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

	public static NotificationBean createNotification(Label label, String description) {
		return new NotificationBean(label, description);
	}

	public static NotificationBean createNotification(Label label, String description, String url) {
		return new NotificationBean(label, description, url);
	}

	public enum Label {
		CONTACT_REQUEST("Contact Request"),
		CONTACT_ACCEPT("New contact"),
		PROJECT_REQUEST("Project Request"),
		PROJECT_ACCEPT("Project"),
		NEW_MESSAGE("Message");

		private final String name;

		Label(String s) {
			name = s;
		}

		public String toString() {
			return this.name;
		}
	}
}

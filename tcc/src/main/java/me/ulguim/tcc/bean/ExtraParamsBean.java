package me.ulguim.tcc.bean;

import in.k2s.sdk.core.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulle on 07/03/17.
 */
public class ExtraParamsBean extends BaseBean {

	private List<Long> requests = new ArrayList<>();

	private List<NotificationBean> notifications = new ArrayList<>();

	public List<Long> getRequests() {
		return requests;
	}

	public void setRequests(List<Long> requests) {
		this.requests = requests;
	}

	public void addRequest(Long id) {
		this.requests.add(id);
	}

	public void removeRequest(Long id) {
		this.requests.removeIf(l -> l.equals(id));
	}

	public boolean existsRequest(Long toFind) {
		return this.requests.stream().filter(id -> id.equals(toFind)).findAny().isPresent();
	}
}

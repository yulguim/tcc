package me.ulguim.tcc.bean;

import in.k2s.sdk.core.bean.BaseBean;

import java.util.List;

/**
 * Created by yulle on 07/03/17.
 */
public class ExtraParamsBean extends BaseBean {

	private List<String> requests;

	public List<String> getRequests() {
		return requests;
	}

	public void setRequests(List<String> requests) {
		this.requests = requests;
	}
}

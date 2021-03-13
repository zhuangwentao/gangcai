package com.gc.system.vo;

import java.io.Serializable;

public class DeleteRlVO implements Serializable {

	private static final long serialVersionUID = 5634586953410559520L;
	private String id;
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

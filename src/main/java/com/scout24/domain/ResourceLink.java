package com.scout24.domain;

public class ResourceLink {
	
	private String url;
	
	private Boolean reachable;
	
	private String error;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getReachable() {
		return reachable;
	}

	public void setReachable(Boolean reachable) {
		this.reachable = reachable;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	

}

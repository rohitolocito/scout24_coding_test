package com.scout24.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Page {
	
	@Valid
	@NotNull
	private String url;
	
	private String title;
	
	private String version;
	
	private Map<String, Integer> headingsCount = new HashMap<>();
	
	private List<ResourceLink> links = new ArrayList<>();
	
	private int internalLinks = 0;
	
	private int externalLinks = 0;
	
	private boolean hasLoginForm = false;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public void setHeadingsCount(Map<String, Integer> headingsCount) {
		this.headingsCount = headingsCount;
	}
	
	public Map<String, Integer> getHeadingsCount() {
		return headingsCount;
	}
	
	public void addLink(ResourceLink link) {
		this.links.add(link);
	}
	
	public void addAllLinks(List<ResourceLink> links) {
		this.links.addAll(links);
	}
	
	public List<ResourceLink> getLinks() {
		return links;
	}
	
	public void incrementInternalLinks() {
		this.internalLinks++;
	}
	
	public void incrementExternalLinks() {
		this.externalLinks++;
	}

	public int getInternalLinks() {
		return internalLinks;
	}

	public int getExternalLinks() {
		return externalLinks;
	}
	
	public void setHasLoginForm(boolean hasLoginForm) {
		this.hasLoginForm = hasLoginForm;
	}
	
	public boolean getHasLoginForm() {
		return hasLoginForm;
	}
	
	
}

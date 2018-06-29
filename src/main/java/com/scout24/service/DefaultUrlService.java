package com.scout24.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

import com.scout24.domain.ResourceLink;

@Component
public class DefaultUrlService implements UrlService {
	
	private ExecutorService executorService;
	
	private UrlValidator urlValidator;
	
	public DefaultUrlService() {
		this.executorService = Executors.newCachedThreadPool();
		this.urlValidator = new UrlValidator(new String[] {"http", "https"});
	}
	
	@Override
	public boolean isValid(String url) {
		return urlValidator.isValid(url);
	}
	
	@Override
	public boolean isSameDomain(String parentUrl, String childUrl) {
		try {
			URL urlObject = new URL(parentUrl);
			String host = urlObject.getHost();
			if (host.startsWith("www.")) {
				host = host.substring(4);
			}
			return childUrl.contains(host);
		} catch (MalformedURLException e) {
			return false;
		}
	}
	
	@Override
	public void updateReachability(ResourceLink link) {
		CompletableFuture
			.supplyAsync(() ->  {
				try {
					new URL(link.getUrl()).getHost();
					return true;
				} catch (MalformedURLException e) {
					link.setError("Malformed Url");
					return false;
				}}, executorService)
			.thenAccept(reachable -> link.setReachable(reachable));
			
	}
}

package com.scout24.service;

import com.scout24.domain.ResourceLink;

public interface UrlService {
	boolean isValid(String url);
	boolean isSameDomain(String url1, String url2);
	void updateReachability(ResourceLink link);
}

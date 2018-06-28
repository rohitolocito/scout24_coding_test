package com.scout24.service;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scout24.domain.Page;
import com.scout24.domain.ResourceLink;
import com.scout24.exceptions.HostNotReachableException;
import com.scout24.exceptions.MalformedUrlException;
import com.scout24.util.HtmlPageHelper;

@Component
public class HtmlPageService implements PageService {
	
	@Autowired
	private UrlService urlValidationService;
	
	@Autowired
	private UrlService urlService;

	@Override
	public Page analyze(Page page) throws Exception {

		if (!urlValidationService.isValid(page.getUrl())) {
			throw new MalformedUrlException("failed to validate page");
		}
		
		try {
			Document document = Jsoup.connect(page.getUrl()).get();
			processDocument(document, page);
			return page;
		} catch (IOException e) {
			throw new HostNotReachableException("failed to fetch the page");
		}
	}
	
	private void processDocument(Document document, Page page) {
		
		page.setTitle(document.title());
		page.setVersion(HtmlPageHelper.getVersion(document));
		page.setHasLoginForm(HtmlPageHelper.hasLoginForm(document));
		page.addAllLinks(processLinkElements(HtmlPageHelper.getAllLinks(document), page));
		
	}
	
	private List<ResourceLink> processLinkElements(Elements links, Page page) {
		
		return links
				.stream()
				.map(element -> {
						ResourceLink resourceLink = new ResourceLink();
						resourceLink.setUrl(HtmlPageHelper.getLinkRef(element));
						urlService.updateReachability(resourceLink);
						if (urlService.isSameDomain(resourceLink.getUrl(), page.getUrl())) {
							page.incrementInternalLinks();
						} else {
							page.incrementExternalLinks();
						}
						return resourceLink;
				})
				.collect(Collectors.toList());
	}

}

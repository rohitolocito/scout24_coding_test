package com.scout24.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doCallRealMethod;

import org.jsoup.nodes.Document;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.scout24.domain.Page;
import com.scout24.domain.ResourceLink;
import com.scout24.exceptions.MalformedUrlException;

@RunWith(MockitoJUnitRunner.class)
public class HtmlPageServiceTest {
	
	@Mock
	private UrlService urlService;
	
	@InjectMocks
	private HtmlPageService htmlPageService;
	
	@Test(expected = MalformedUrlException.class)
	public void test_invalid_url() throws Exception {
		
		Page page = new Page();
		page.setUrl("invalidurl.com.de");
		
		when(urlService.isValid(any(String.class))).thenReturn(false);
		
		htmlPageService.analyze(page);
	}
	
	@Test
	public void test_html_document_title() throws Exception {
		
		Page page = new Page();
		page.setUrl("www.someurl.com");
		
		Document doc = Document.createShell(page.getUrl());
		doc.append("<html><head><title>Test Html</title></head>"
                + "<body>Html Body</body></html>");

		htmlPageService.processDocument(doc, page);
		assertEquals("Test Html", page.getTitle());
	}
	
	
	@Test
	public void test_html_document_headingsCount() throws Exception {
		
		Page page = new Page();
		page.setUrl("www.someurl.com");
		
		Document doc = Document.createShell(page.getUrl());
		doc.append("<html><head><title>Test Html</title></head>"
                + "<body><h1> hey </h1> <h2> hello </h2> <h1> hi </h1></body></html>");

		htmlPageService.processDocument(doc, page);
		assertEquals(6, page.getHeadingsCount().size());
		assertEquals(new Integer(2), page.getHeadingsCount().get("h1"));
		assertEquals(new Integer(0), page.getHeadingsCount().get("h3"));
		
	}
	
	
	@Test
	public void test_html_document_links_count() throws Exception {
		
		Page page = new Page();
		page.setUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_a_href");
		
		when(urlService.isSameDomain(any(String.class), any(String.class))).thenReturn(true);
		
		Document doc = Document.createShell(page.getUrl());
		doc.append("<html><head><title>Test Html</title></head>"
                + "<body><h1> hey </h1> <h2> hello </h2> <h1> hi </h1>"
				+ "<p>An absolute URL: <a href=\"https://www.w3schools.com\">W3Schools</a></p>"
				+ "<p>A relative URL: <a href=\"tag_a.asp\">The a tag</a></p>"
                + "</body></html>");

		htmlPageService.processDocument(doc, page);
		assertEquals(2, page.getInternalLinks());
		
	}

}

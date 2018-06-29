package com.scout24.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.scout24.domain.ResourceLink;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUrlServiceTest {
	

	@InjectMocks
	private DefaultUrlService urlService;
	
	
	@Test
	public void test_url_reachability() throws InterruptedException {
		
		ResourceLink link = new ResourceLink();
		link.setUrl("https://www.google.com");
		
		urlService.updateReachability(link);
		
		Thread.sleep(1000);
		
		assertTrue(link.getReachable());
	}
	
	@Test
	public void test_url_samedomain() throws InterruptedException {
		
		assertTrue(urlService.isSameDomain("https://www.github.com", "https://help.github.com"));
	}

}

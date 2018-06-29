package com.scout24.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scout24.domain.Page;
import com.scout24.exceptions.MalformedUrlException;
import com.scout24.service.HtmlPageService;
import com.scout24.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class HtmlPageControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private HtmlPageService htmlPageService;
	
	@InjectMocks
	private HtmlPageController htmlPageController;
	
	@Before
	public void setup() 
	{
		mockMvc = MockMvcBuilders.standaloneSetup(htmlPageController).build();
	}
	
	@Test
	public void test_valid_html() throws Exception {
		
		Page page = new Page();
		page.setUrl("www.google.com");
	
		
		mockMvc.perform(post("/v1/process")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(TestUtils.toString(page)))
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void test_bad_request() throws Exception {
		
		Page page = new Page();
		page.setUrl("www.invalidurl.com");
		
		doThrow(MalformedUrlException.class).when(htmlPageService).analyze(any(Page.class));

		
		mockMvc.perform(post("/v1/process")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(TestUtils.toString(page)))
		.andExpect(status().isBadRequest());
	}

}

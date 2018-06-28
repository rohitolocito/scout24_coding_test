package com.scout24.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scout24.domain.Page;
import com.scout24.service.HtmlPageService;

@RestController
@RequestMapping("v1")
public class HtmlPageController {
	
	@Autowired
	private HtmlPageService htmlPageService;

	@PostMapping("/process")
    public Page processHtmlPage(@RequestBody Page page) throws Exception
    {
		return htmlPageService.analyze(page);
    }
}

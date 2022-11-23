package com.xdesign.cake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.cake.contents.ContentsRetriever;
import com.xdesign.cake.domain.Contents;

@RestController
public class ContentsController {

	@Autowired
	private ContentsRetriever contentsRetriever;

	@GetMapping("/java/contents")
	public Contents getContents() {
		return contentsRetriever.retrieveAllContents();
	}
}

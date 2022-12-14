package com.xdesign.cake.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.cake.contents.ContentsStore;
import com.xdesign.cake.domain.Contents;

@RestController
public class ContentsController {

	private ContentsStore contentsStore;

	public ContentsController( final ContentsStore contentsStore ) {
		this.contentsStore = contentsStore;
	}

	@GetMapping("/java/contents")
	public Contents getContents() {
		return contentsStore.retrieveContents();
	}
}

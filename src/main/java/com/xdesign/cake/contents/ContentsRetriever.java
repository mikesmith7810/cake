package com.xdesign.cake.contents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xdesign.cake.domain.Contents;

@Component
public class ContentsRetriever {
	@Autowired
	public ContentsStore contentsStore;

	public Contents retrieveAllContents() {
		return contentsStore.getContents();
	}
}

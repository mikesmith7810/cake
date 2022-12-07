package com.xdesign.cake.contents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xdesign.cake.domain.Chapter;
import com.xdesign.cake.domain.Contents;
import com.xdesign.cake.domain.Example;

import lombok.Data;

@Data
@Component
public class ContentsRetriever {
	@Autowired
	public ContentsStore contentsStore;

	private List<Example> examples = new ArrayList<>();

	public Contents retrieveAllContents() {
		cacheContents();

		return contentsStore.getContents();
	}

	private void cacheContents() {
		if ( contentsStore.getContents() == null ) {
			Set<Chapter> uniqueChapters = new HashSet<>();

			examples.stream()
					.forEach( example -> uniqueChapters.add( Chapter.builder()
							.name( example.getChapter() )
							.examples( new ArrayList<>() )
							.build() ) );

			uniqueChapters.stream().forEach( chapter -> {
				examples.stream()
						.filter( example -> chapter.getName().equals( example.getChapter() ) )
						.forEach( example -> chapter.getExamples().add( example ) );
			} );

			contentsStore.setContents( Contents.builder()
					.chapters( new ArrayList<Chapter>( uniqueChapters ) )
					.build() );
		}
	}
}

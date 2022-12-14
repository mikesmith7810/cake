package com.xdesign.cake.contents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.retriever.CodeRetriever;
import com.xdesign.cake.domain.Chapter;
import com.xdesign.cake.domain.Contents;
import com.xdesign.cake.domain.Example;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Component
public class ContentsStore {
	private Contents contents;

	private List<Example> examples = new ArrayList<>();

	private final CodeRetriever codeRetriever;

	public Contents retrieveContents() {
		if ( contents == null ) {
			cacheContents();
		}
		return contents;
	}

	public void cacheContents() {

		Set<Chapter> uniqueChapters = new HashSet<>();

		examples.stream()
				.forEach( example -> uniqueChapters.add( Chapter.builder()
						.name( example.getChapter() )
						.examples( new ArrayList<>() )
						.build() ) );

		uniqueChapters.stream().forEach( chapter -> {
			examples.stream()
					.filter( example -> chapter.getName().equals( example.getChapter() ) )
					.forEach( example -> {
						try {
							example.setSourceCode(
									codeRetriever.retrieveCodeFor( example.getGithubLocation() ) );
						} catch ( IOException e ) {
							log.info( "Error reading soruce code : " + e.getMessage() );
						}
						chapter.getExamples().add( example );
					} );
		} );

		log.info( "Creating contents with chapters : " + uniqueChapters.size() );

		setContents(
				Contents.builder().chapters( new ArrayList<>( uniqueChapters ) ).build() );
	}
}

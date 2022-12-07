package com.xdesign.cake;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xdesign.cake.domain.Chapter;
import com.xdesign.cake.domain.Example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScratchPad {

	public static Set<Chapter> chapters = new HashSet<>();
	public static List<Example> examples = new ArrayList<>();

	public static void main( String[] args ) {

		chapterExampleCreation();
	}

	private static void chapterExampleCreation() {
		createTestData();

		Set<Chapter> uniqueChapters = new HashSet<>();
		examples.stream()
				.forEach( example -> uniqueChapters.add( Chapter.builder()
						.name( example.getChapter() )
						.examples( new ArrayList<>() )
						.build() ) );

		log.info( "created unique chapters" );

		uniqueChapters.stream().forEach( chapter -> {
			examples.stream()
					.filter( example -> chapter.getName().equals( example.getChapter() ) )
					.forEach( example -> chapter.getExamples().add( example ) );
		} );

		log.info( "added examples" );
	}

	private static void createTestData() {
		chapters.add( Chapter.builder().name( "Functions" ).build() );
		chapters.add( Chapter.builder().name( "Optionals" ).build() );
		chapters.add( Chapter.builder().name( "Lambdas" ).build() );

		examples = List.of(
				Example.builder()
						.chapter( "Functions" )
						.description( "function 1 example" )
						.build(),
				Example.builder().chapter( "Lambdas" ).description( "lambda 1 example" ).build(),
				Example.builder()
						.chapter( "Functions" )
						.description( "function 2 example" )
						.build(),
				Example.builder()
						.chapter( "Optionals" )
						.description( "optioanls 1 example" )
						.build(),
				Example.builder()
						.chapter( "Optionals" )
						.description( "optioanls 2 example" )
						.build(),
				Example.builder().chapter( "Lambdas" ).description( "lambdas 2 example" ).build(),
				Example.builder()
						.chapter( "Functions" )
						.description( "function 3 example" )
						.build(),
				Example.builder()
						.chapter( "Functions" )
						.description( "function example" )
						.build() );
	}
}

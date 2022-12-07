package com.xdesign.cake.demonstrators.functionalinterface;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.annotation.CodeExample;

@Component
public class PredicateDemonstrator extends Demonstrator {
	@CodeExample(name = "Predicate",
			description = "Predicate Code Example. Filters words starting with 'S'.",
			api = "/java/functionalinterface",
			githubLocation = "https://github.com/mikesmith7810/cake/blob/master/src/main/java/com/xdesign/cake/functionalinterface/PredicateDemonstrator.java",
			chapter = "Functional Interfaces")
	public boolean demoFunction( final List<String> word ) {
		final Predicate<String> startsWithPredicate = w -> w.startsWith( "S" );

		return startsWithPredicate.test( word.get( 0 ) );
	}

	@CodeExample(name = "Predicate",
			description = "Predicate Code Example. Filters supplied words starting with supplied letter.",
			api = "/java/functionalinterface",
			githubLocation = "https://github.com/mikesmith7810/cake/blob/master/src/main/java/com/xdesign/cake/functionalinterface/PredicateDemonstrator.java",
			chapter = "Functional Interfaces")
	public List<String> demoFunction( final List<String> words, final String letter ) {
		return words.stream()
				.filter( word -> filterListByLetter( word, w -> w.startsWith( letter ) ) )
				.collect( Collectors.toList() );
	}

	private boolean filterListByLetter( final String word, final Predicate<String> predicate ) {
		return predicate.test( word );
	}
}

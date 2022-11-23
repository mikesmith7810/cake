package com.xdesign.cake.functionalinterface;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PredicateDemonstrator extends Demonstrator {

	public boolean demoFunction( final List<String> word ) {
		final Predicate<String> startsWithPredicate = w -> w.startsWith( "S" );

		return startsWithPredicate.test( word.get( 0 ) );
	}

	public List<String> demoFunction( final List<String> words, final String letter ) {
		return words.stream()
				.filter( word -> filterListByLetter( word, w -> w.startsWith( letter ) ) )
				.collect( Collectors.toList() );
	}

	private boolean filterListByLetter( final String word, final Predicate<String> predicate ) {
		return predicate.test( word );
	}
}

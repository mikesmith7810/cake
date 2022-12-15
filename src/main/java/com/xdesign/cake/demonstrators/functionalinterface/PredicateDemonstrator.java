package com.xdesign.cake.demonstrators.functionalinterface;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.annotation.CodeExample;
import com.xdesign.cake.task.TaskType;

@Component
public class PredicateDemonstrator extends Demonstrator {
	@CodeExample(name = "Predicate",
			description = "Predicate Code Example. Filters words starting with 'S'.",
			api = "/java/functionalinterface",
			chapter = "Functional Interfaces",
			taskType = TaskType.PREDICATE,
			slashCommand = "/learnjava/functionalinterfaces",
			slashParameters = "PREDICATE super red star")

	public boolean demoFunction( final List<String> word ) {
		final Predicate<String> startsWithPredicate = w -> w.startsWith( "S" );

		return startsWithPredicate.test( word.get( 0 ) );
	}

	@CodeExample(name = "Predicate",
			description = "Predicate Code Example. Filters supplied words starting with supplied letter.",
			api = "/java/functionalinterface",
			chapter = "Functional Interfaces",
			taskType = TaskType.PREDICATE2,
			slashCommand = "/learnjava/functionalinterfaces",
			slashParameters = "PREDICATE2 s super red star")
	public List<String> demoFunction( final String letter, final List<String> words ) {
		return words.stream()
				.filter( word -> filterListByLetter( word, w -> w.startsWith( letter ) ) )
				.collect( Collectors.toList() );
	}

	private boolean filterListByLetter( final String word, final Predicate<String> predicate ) {
		return predicate.test( word );
	}
}

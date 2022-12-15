package com.xdesign.cake.demonstrators.optional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.annotation.CodeExample;
import com.xdesign.cake.demonstrators.functionalinterface.Demonstrator;
import com.xdesign.cake.task.TaskType;

@Component
public class CreationDemonstrator extends Demonstrator {
	@CodeExample(name = "Optional Creation",
			description = "Creates an Optional String",
			api = "/java/optional",
			githubLocation = "https://raw.githubusercontent.com/mikesmith7810/cake/master/src/main/java/com/xdesign/cake/demonstrators/optional/CreationDemonstrator.java",
			chapter = "Optionals",
			taskType = TaskType.CREATION,
			slashCommand = "/learnjavaoptionals CREATION")
	public Optional<String> demoFunction( final List<String> input ) {
		final Optional<String> optional = Optional.of( input.get( 0 ) );

		return optional;
	}
}

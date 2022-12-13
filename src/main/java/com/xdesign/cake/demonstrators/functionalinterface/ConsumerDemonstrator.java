package com.xdesign.cake.demonstrators.functionalinterface;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.annotation.CodeExample;
import com.xdesign.cake.task.TaskType;

@Component
public class ConsumerDemonstrator extends Demonstrator {
	@CodeExample(name = "Consumer",
			description = "Consumer Code Example. Sends a message to the console",
			api = "/java/functionalinterface",
			githubLocation = "https://raw.githubusercontent.com/mikesmith7810/cake/master/src/main/java/com/xdesign/cake/demonstrators/functionalinterface/ConsumerDemonstrator.java",
			chapter = "Functional Interfaces",
			taskType = TaskType.CONSUMER)
	public String demoFunction( final List<String> input ) {
		final Consumer<String> consumer = string -> System.out.println( string );

		consumer.accept( input.get( 0 ) );
		return "SENT TO CONSOLE";
	}
}

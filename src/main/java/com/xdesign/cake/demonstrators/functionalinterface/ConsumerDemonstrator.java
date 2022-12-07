package com.xdesign.cake.demonstrators.functionalinterface;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.annotation.CodeExample;

@Component
public class ConsumerDemonstrator extends Demonstrator {
	@CodeExample(name = "Consumer",
			description = "Consumer Code Example. Sends a message to the console",
			api = "/java/functionalinterface",
			githubLocation = "https://github.com/mikesmith7810/cake/blob/master/src/main/java/com/xdesign/cake/functionalinterface/SupplierDemonstrator.java",
			chapter = "Functional Interfaces")
	public String demoFunction( final List<String> input ) {
		final Consumer<String> consumer = string -> System.out.println( string );

		consumer.accept( input.get( 0 ) );
		return "SENT TO CONSOLE";
	}
}

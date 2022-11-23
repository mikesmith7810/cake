package com.xdesign.cake.functionalinterface;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.annotation.CodeExample;

@Component
public class FunctionDemonstrator extends Demonstrator {
	@CodeExample(description = "Function Code Example. Reverses a string.",
			api = "/java/functionalinterface",
			githubLocation = "https://github.com/mikesmith7810/cake/blob/master/src/main/java/com/xdesign/cake/functionalinterface/FunctionDemonstrator.java")
	public String demoFunction( final List<String> input ) {
		final Function<String, String> reverser = s -> {
			StringBuilder stringBuilder = new StringBuilder( s );

			return stringBuilder.reverse().toString();
		};

		return reverser.apply( input.get( 0 ) );
	}
}

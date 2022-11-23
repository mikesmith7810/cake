package com.xdesign.cake.functionalinterface;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class FunctionDemonstrator extends Demonstrator {
	public String demoFunction( final String input ) {
		Function<String, String> reverser = s -> {
			StringBuilder stringBuilder = new StringBuilder( s );

			return stringBuilder.reverse().toString();
		};

		return reverser.apply( input );
	}
}

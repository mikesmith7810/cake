package com.xdesign.cake.functionalinterface;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class FunctionDemonstrator extends Demonstrator {
	public String demoFunction( final List<String> input ) {
		Function<String, String> reverser = s -> {
			StringBuilder stringBuilder = new StringBuilder( s );

			return stringBuilder.reverse().toString();
		};

		return reverser.apply( input.get( 0 ) );
	}
}

package com.xdesign.cake.functionalinterface;

import java.util.function.Function;

public class FunctionDemonstrator implements FunctionalInterfaceDemonstrator {
	public String demoFunction( final String input ) {
		Function<String, String> reverser = s -> {
			StringBuilder stringBuilder = new StringBuilder( s );

			return stringBuilder.reverse().toString();
		};

		return reverser.apply( input );
	}

}

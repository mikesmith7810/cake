package com.xdesign.cake.functionalinterface;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

@Component
public class ConsumerDemonstrator extends Demonstrator {

	public String demoFunction( String input ) {
		Consumer<String> consumer = string -> System.out.println( string );

		consumer.accept( input );
		return "";
	}
}

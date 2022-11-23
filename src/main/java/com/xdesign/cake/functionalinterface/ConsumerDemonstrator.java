package com.xdesign.cake.functionalinterface;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

@Component
public class ConsumerDemonstrator extends Demonstrator {

	public String demoFunction( List<String> input ) {
		Consumer<String> consumer = string -> System.out.println( string );

		consumer.accept( input.get( 0 ) );
		return "SENT TO CONSOLE";
	}
}

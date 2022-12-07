package com.xdesign.cake.demonstrators.streams;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.annotation.CodeExample;
import com.xdesign.cake.demonstrators.functionalinterface.Demonstrator;

@Component
public class ForEachDemonstrator extends Demonstrator {
	@CodeExample(name = "Streams ForEach",
			description = "Outputs list of strings to console",
			api = "/java/streams",
			githubLocation = "https://github.com/mikesmith7810/cake/blob/master/src/main/java/com/xdesign/cake/streams/ForEachDemonstrator.java",
			chapter = "Streams")
	public String demoFunction( final List<String> input ) {

		input.stream().forEach( word -> System.out.println( word ) );

		return "SENT TO CONSOLE";
	}
}

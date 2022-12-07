package com.xdesign.cake.demonstrators.optional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.annotation.CodeExample;
import com.xdesign.cake.demonstrators.functionalinterface.Demonstrator;

@Component
public class CreationDemonstrator extends Demonstrator {
    @CodeExample(name = "Optional Creation",
			description = "Creates an Optional.",
			api = "/java/optional",
			githubLocation = "https://github.com/mikesmith7810/cake/blob/master/src/main/java/com/xdesign/cake/functionalinterface/FunctionDemonstrator.java",
			chapter = "Optionals")
    public Optional<String> demoFunction(final List<String> input) {
        final Optional<String> optional = Optional.of(input.get( 0 ));

        return optional;
    }
}

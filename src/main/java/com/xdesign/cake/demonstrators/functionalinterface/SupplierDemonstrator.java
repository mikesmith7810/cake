package com.xdesign.cake.demonstrators.functionalinterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.annotation.CodeExample;

@Component
public class SupplierDemonstrator extends Demonstrator {
	@CodeExample(name = "Supplier",
			description = "Supplier Code Example. Supplies the current date.",
			api = "/java/functionalinterface",
			githubLocation = "https://github.com/mikesmith7810/cake/blob/master/src/main/java/com/xdesign/cake/functionalinterface/ConsumerDemonstrator.java",
			chapter = "Functional Interfaces")
	public String demoFunction() {
		final Supplier<String> dateSupplier = () -> LocalDate.now()
				.format( DateTimeFormatter.ISO_LOCAL_DATE );

		return dateSupplier.get();
	}
}

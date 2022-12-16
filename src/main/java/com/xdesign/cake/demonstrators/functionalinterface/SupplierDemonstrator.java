package com.xdesign.cake.demonstrators.functionalinterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.annotation.CodeExample;
import com.xdesign.cake.task.TaskType;

@Component
public class SupplierDemonstrator extends Demonstrator {
	@CodeExample(name = "Supplier",
			description = "Supplier Code Example. Supplies the current date.",
			api = "/java/functionalinterface",
			chapter = "Functional Interfaces",
			taskType = TaskType.SUPPLIER,
			slashCommand = "/learnjava/functionalinterfaces",
			slashParameters = "SUPPLIER")

	public String runExampleFor() {
		final Supplier<String> dateSupplier = () -> LocalDate.now()
				.format( DateTimeFormatter.ISO_LOCAL_DATE );

		return dateSupplier.get();
	}
}

package com.xdesign.cake.functionalinterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

@Component
public class SupplierDemonstrator {
	public String demoFunction() {
		Supplier<String> dateSupplier = () -> LocalDate.now()
				.format( DateTimeFormatter.ISO_LOCAL_DATE );

		return dateSupplier.get();
	}
}

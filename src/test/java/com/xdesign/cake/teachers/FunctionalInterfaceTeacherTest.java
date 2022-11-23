package com.xdesign.cake.teachers;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;
import com.xdesign.cake.functionalinterface.ConsumerDemonstrator;
import com.xdesign.cake.functionalinterface.FunctionDemonstrator;
import com.xdesign.cake.functionalinterface.PredicateDemonstrator;
import com.xdesign.cake.functionalinterface.SupplierDemonstrator;
import com.xdesign.cake.task.FunctionalInterfaceType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FunctionalInterfaceTeacherTest {

	FunctionalInterfaceTeacher functionalInterfacesTeacher;

	@Mock
	FunctionDemonstrator functionDemonstrator;

	@Mock
	ConsumerDemonstrator consumerDemonstrator;

	@Mock
	PredicateDemonstrator predicateDemonstrator;

	@Mock
	SupplierDemonstrator supplierDemonstrator;

	@BeforeEach
	void setup() {
		this.functionalInterfacesTeacher = new FunctionalInterfaceTeacher( functionDemonstrator,
				consumerDemonstrator,
				predicateDemonstrator,
				supplierDemonstrator );
	}

	@Test
	void shouldUseFunctionToReverseString() {
		final String result = functionalInterfacesTeacher
				.demoFunction( FunctionalInterfaceType.FUNCTION, ImmutableList.of( "iamatest" ) );

		verify( functionDemonstrator ).demoFunction( ImmutableList.of( "iamatest" ) );
	}

	@Test
	void shouldUseConsumerToPrintToConsole() {
		functionalInterfacesTeacher.demoFunction( FunctionalInterfaceType.CONSUMER,
				ImmutableList.of( "Hello World" ) );

		verify( consumerDemonstrator ).demoFunction( ImmutableList.of( "Hello World" ) );
	}

	@Test
	void shouldUsePredicateToCheckStringStartsWithLetter() {
		final String result = functionalInterfacesTeacher
				.demoFunction( FunctionalInterfaceType.PREDICATE, ImmutableList.of( "Super" ) );

		verify( predicateDemonstrator ).demoFunction( ImmutableList.of( "Super" ) );
	}

	@Test
	void shouldUseSupplierToGetTheCurrentDate() {
		final String result = functionalInterfacesTeacher
				.demoFunction( FunctionalInterfaceType.SUPPLIER, ImmutableList.of( "Super" ) );

		verify( supplierDemonstrator ).demoFunction();
	}
}

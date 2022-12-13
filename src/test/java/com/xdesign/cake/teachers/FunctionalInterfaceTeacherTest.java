package com.xdesign.cake.teachers;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;
import com.xdesign.cake.contents.ContentsStore;
import com.xdesign.cake.demonstrators.functionalinterface.ConsumerDemonstrator;
import com.xdesign.cake.demonstrators.functionalinterface.FunctionDemonstrator;
import com.xdesign.cake.demonstrators.functionalinterface.PredicateDemonstrator;
import com.xdesign.cake.demonstrators.functionalinterface.SupplierDemonstrator;
import com.xdesign.cake.task.TaskType;

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

	@Mock
	ContentsStore contentsStore;

	@BeforeEach
	void setup() {
		this.functionalInterfacesTeacher = new FunctionalInterfaceTeacher( functionDemonstrator,
				consumerDemonstrator,
				predicateDemonstrator,
				supplierDemonstrator,
				contentsStore );
	}

	@Test
	void shouldUseFunctionToReverseString() {
		final String result = functionalInterfacesTeacher.demoFunction( TaskType.FUNCTION,
				ImmutableList.of( "iamatest" ) );

		verify( functionDemonstrator ).demoFunction( ImmutableList.of( "iamatest" ) );
	}

	@Test
	void shouldUseConsumerToPrintToConsole() {
		functionalInterfacesTeacher.demoFunction( TaskType.CONSUMER,
				ImmutableList.of( "Hello World" ) );

		verify( consumerDemonstrator ).demoFunction( ImmutableList.of( "Hello World" ) );
	}

	@Test
	void shouldUsePredicateToCheckStringStartsWithLetter() {
		final String result = functionalInterfacesTeacher.demoFunction( TaskType.PREDICATE,
				ImmutableList.of( "Super" ) );

		verify( predicateDemonstrator ).demoFunction( ImmutableList.of( "Super" ) );
	}

	@Test
	void shouldUseSupplierToGetTheCurrentDate() {
		final String result = functionalInterfacesTeacher.demoFunction( TaskType.SUPPLIER,
				ImmutableList.of( "Super" ) );

		verify( supplierDemonstrator ).demoFunction();
	}
}

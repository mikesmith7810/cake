package com.xdesign.cake.teachers;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xdesign.cake.functionalinterface.ConsumerDemonstrator;
import com.xdesign.cake.functionalinterface.FunctionDemonstrator;
import com.xdesign.cake.functionalinterface.PredicateDemonstrator;
import com.xdesign.cake.task.FunctionalInterfaceType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FunctionalInterfaceTeacherTest {

	@InjectMocks
	FunctionalInterfaceTeacher functionalInterfacesTeacher;

	@Mock
	FunctionDemonstrator functionDemonstrator;

	@Mock
	ConsumerDemonstrator consumerDemonstrator;

	@Mock
	PredicateDemonstrator predicateDemonstrator;

	@BeforeAll
	void setup() {
		MockitoAnnotations.openMocks( this );
		functionalInterfacesTeacher = new FunctionalInterfaceTeacher();
	}

	@Test
	void shouldUseFunctionToReverseString() {
		final String result = functionalInterfacesTeacher
				.demoFunction( FunctionalInterfaceType.FUNCTION, "iamatest" );

		verify( functionDemonstrator ).demoFunction( "iamatest" );
	}

	@Test
	void shouldUseConsumerToPrintToConsole() {
		functionalInterfacesTeacher.demoFunction( FunctionalInterfaceType.CONSUMER, "Hello World" );

		verify( consumerDemonstrator ).demoFunction( "Hello World" );
	}

	@Test
	void shouldUsePredicateToCheckStringStartsWithLetter() {
		final String result = functionalInterfacesTeacher
				.demoFunction( FunctionalInterfaceType.PREDICATE, "Super" );

		verify( predicateDemonstrator ).demoFunction( "Super" );
	}
}

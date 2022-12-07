package com.xdesign.cake.teachers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;
import com.xdesign.cake.demonstrators.optional.CreationDemonstrator;
import com.xdesign.cake.task.OptionalType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class OptionalTeacherTest {

	OptionalTeacher optionalTeacher;

	@Mock
	CreationDemonstrator creationDemonstrator;

	@BeforeEach
	public void setup() {
		this.optionalTeacher = new OptionalTeacher( creationDemonstrator);
	}

	@Test
	public void shouldCallDemonstrator() {
		when(creationDemonstrator.demoFunction(ImmutableList.of( "iamatest" ))).thenReturn(Optional.of("iamatest"));

		final String result = optionalTeacher
				.demoFunction( OptionalType.CREATION, ImmutableList.of( "iamatest" ) );

		verify( creationDemonstrator ).demoFunction( ImmutableList.of( "iamatest" ) );
	}


}
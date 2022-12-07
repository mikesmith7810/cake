package com.xdesign.cake.teachers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;
import com.xdesign.cake.demonstrators.streams.ForEachDemonstrator;
import com.xdesign.cake.task.StreamsType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class StreamsTeacherTest {

	StreamsTeacher streamsTeacher;

	@Mock
	ForEachDemonstrator forEachDemonstrator;

	@BeforeEach
	public void setup() {
		this.streamsTeacher = new StreamsTeacher( forEachDemonstrator );
	}

	@Test
	public void shouldCallDemonstrator() {
		when( forEachDemonstrator
				.demoFunction( ImmutableList.of( "iamatest", "andiamatestaswell" ) ) )
						.thenReturn( "iamatest//nandiamatestaswell" );

		final String result = streamsTeacher.demoFunction( StreamsType.FOREACH,
				ImmutableList.of( "iamatest", "andiamatestaswell" ) );

		verify( forEachDemonstrator )
				.demoFunction( ImmutableList.of( "iamatest", "andiamatestaswell" ) );
	}

}

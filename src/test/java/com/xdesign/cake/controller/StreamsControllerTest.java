package com.xdesign.cake.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.xdesign.cake.task.StreamsTask;
import com.xdesign.cake.task.StreamsTaskResult;
import com.xdesign.cake.task.StreamsType;
import com.xdesign.cake.teachers.StreamsTeacher;

@WebMvcTest(StreamsController.class)
class StreamsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StreamsTeacher streamsTeacher;

	public static String asJsonString( final Object obj ) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString( obj );
	}

	@ParameterizedTest
	@EnumSource(StreamsType.class)
	void shouldDelegateStreamsTaskToTeacher( final StreamsType streamsType ) throws Exception {

		final StreamsTask streamsTask = StreamsTask.builder()
				.taskType( streamsType )
				.parameters( ImmutableList.of( "thisisatest" ) )
				.build();

		final StreamsTaskResult streamsTaskResult = StreamsTaskResult.builder()
				.type( streamsType )
				.value( "tsetasisiht" )
				.build();

		when( streamsTeacher.runLearningMaterial( streamsTask ) ).thenReturn( streamsTaskResult );

		this.mockMvc
				.perform( get( "/java/streams" ).content( asJsonString( streamsTask ) )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.type" ).value( streamsType.toString() ) )
				.andExpect( jsonPath( "$.value" ).value( "tsetasisiht" ) );

		verify( streamsTeacher, times( 1 ) ).runLearningMaterial( streamsTask );
	}

}

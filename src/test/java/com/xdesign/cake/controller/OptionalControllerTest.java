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
import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;
import com.xdesign.cake.task.TaskType;
import com.xdesign.cake.teachers.OptionalTeacher;

@WebMvcTest(OptionalController.class)
class OptionalControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OptionalTeacher optionalTeacher;

	public static String asJsonString( final Object obj ) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString( obj );
	}

	@ParameterizedTest
	@EnumSource(TaskType.class)
	void shouldDelegateOptionalTaskToTeacher( final TaskType optionalType ) throws Exception {

		final Task task = Task.builder()
				.taskType( optionalType )
				.parameters( ImmutableList.of( "thisisatest" ) )
				.build();

		final TaskResult taskResult = TaskResult.builder()
				.type( optionalType )
				.value( "tsetasisiht" )
				.build();

		when( optionalTeacher.teachThis( task ) ).thenReturn( taskResult );

		this.mockMvc
				.perform( get( "/java/optional" ).content( asJsonString( task ) )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.type" ).value( optionalType.toString() ) )
				.andExpect( jsonPath( "$.value" ).value( "tsetasisiht" ) );

		verify( optionalTeacher, times( 1 ) ).teachThis( task );
	}

}

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
import com.xdesign.cake.task.FunctionalInterfaceType;
import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;
import com.xdesign.cake.teachers.FunctionalInterfaceTeacher;

@WebMvcTest(FunctionalInterfacesController.class)
class FunctionalInterfacesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FunctionalInterfaceTeacher functionalInterfaceTeacher;

	public static String asJsonString( final Object obj ) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString( obj );
	}

	@ParameterizedTest
	@EnumSource(FunctionalInterfaceType.class)
	void shouldDelegateFunctionalInterfaceTaskToTeacher(
			final FunctionalInterfaceType functionalInterfaceType ) throws Exception {

		final Task functionTask = Task.builder()
				.taskType( functionalInterfaceType.getValue() )
				.parameter( "thisisatest" )
				.build();

		final TaskResult functionTaskResult = TaskResult.builder()
				.type( functionalInterfaceType.getValue() )
				.value( "tsetasisiht" )
				.build();

		when( functionalInterfaceTeacher.runLearningMaterial( functionTask ) )
				.thenReturn( functionTaskResult );

		this.mockMvc
				.perform( get( "/java/functionalinterface" ).content( asJsonString( functionTask ) )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.type" ).value( functionalInterfaceType.getValue() ) )
				.andExpect( jsonPath( "$.value" ).value( "tsetasisiht" ) );

		verify( functionalInterfaceTeacher, times( 1 ) ).runLearningMaterial( functionTask );
	}

}

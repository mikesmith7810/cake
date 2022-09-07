package com.xdesign.cake.admin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdesign.cake.domain.Cake;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldRetrieveAllCakes() throws Exception {

		this.mockMvc.perform( get( "/admin/cakes" ).accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.cakes[0].id" ).value( 1 ) )
				.andExpect( jsonPath( "$.cakes[0].name" ).value( "Chocolate" ) );
	}

	@Test
	void shouldAddANewCake() throws Exception {

		this.mockMvc
				.perform( post( "/admin/cake" )
						.content( asJsonString( Cake.builder().id( 2 ).name( "Cream" ).build() ) )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.cakes[1].id" ).value( 2 ) )
				.andExpect( jsonPath( "$.cakes[1].name" ).value( "Cream" ) );
	}

	@Test
	void shouldUpdateCake() throws Exception {

		this.mockMvc
				.perform( put( "/admin/cake" )
						.content( asJsonString(
								Cake.builder().id( 2 ).name( "Strawberry" ).build() ) )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.cakes[1].id" ).value( 2 ) )
				.andExpect( jsonPath( "$.cakes[1].name" ).value( "Strawberry" ) );
	}

	@Test
	void shouldDeleteANewCake() throws Exception {

		this.mockMvc.perform( delete( "/admin/cake/3" ).accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isNoContent() )
				.andExpect( jsonPath( "$" ).doesNotExist() );
	}

	public static String asJsonString( final Object obj ) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString( obj );
	}

}

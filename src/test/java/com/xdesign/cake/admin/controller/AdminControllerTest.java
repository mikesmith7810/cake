package com.xdesign.cake.admin.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdesign.cake.db.repository.CakeRepository;
import com.xdesign.cake.domain.Cake;
import com.xdesign.cake.helper.RandomWordRetriever;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CakeRepository cakeRepository;

	@MockBean
	private RandomWordRetriever randomWordRetriever;

	@Test
	void shouldRetrieveAllCakes() throws Exception {

		final List<Cake> cakes = new ArrayList<Cake>();
		cakes.add( Cake.builder().id( 1 ).name( "Chocolate" ).build() );
		when( cakeRepository.findAll() ).thenReturn( cakes );

		this.mockMvc.perform( get( "/admin/cakes" ).accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.cakes[0].id" ).value( 1 ) )
				.andExpect( jsonPath( "$.cakes[0].name" ).value( "Chocolate" ) );

		verify( cakeRepository, times( 1 ) ).findAll();
	}

	@Test
	void shouldAddOrUpdateANewCake() throws Exception {

		final Cake newCake = Cake.builder().id( 2 ).name( "Cream" ).build();
		when( cakeRepository.save( newCake ) ).thenReturn( newCake );

		final List<Cake> cakes = new ArrayList<Cake>();
		cakes.add( Cake.builder().id( 1 ).name( "Chocolate" ).build() );
		cakes.add( newCake );
		when( cakeRepository.findAll() ).thenReturn( cakes );

		this.mockMvc
				.perform( post( "/admin/cake" )
						.content( asJsonString( newCake ) )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.cakes[1].id" ).value( 2 ) )
				.andExpect( jsonPath( "$.cakes[1].name" ).value( "Cream" ) );

		verify( cakeRepository, times( 1 ) ).save( newCake );
	}

	@Test
	void shouldCreateXNumberOfRandomCakes() throws Exception {

		final int numberOfCakesToBeCreated = 3;

		final List<String> randomWords = new ArrayList<String>();
		randomWords.add( "Robot" );
		randomWords.add( "Wood" );
		randomWords.add( "Mud" );

		when( randomWordRetriever.getRandomWords( numberOfCakesToBeCreated ) )
				.thenReturn( randomWords );

		final List<Cake> cakes = new ArrayList<Cake>();
		cakes.add( Cake.builder().id( 1 ).name( "Robot" ).build() );
		cakes.add( Cake.builder().id( 2 ).name( "Wood" ).build() );
		cakes.add( Cake.builder().id( 3 ).name( "Mud" ).build() );
		when( cakeRepository.findAll() ).thenReturn( cakes );

		this.mockMvc
				.perform( post( "/admin/cake/magiccreate/" + numberOfCakesToBeCreated )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() );

		verify( randomWordRetriever, times( 1 ) ).getRandomWords( numberOfCakesToBeCreated );
		verify( cakeRepository, times( numberOfCakesToBeCreated ) ).save( any() );
	}

	@Test
	void shouldDeleteANewCake() throws Exception {

		final int cakeIdToBeDeleted = 3;

		this.mockMvc
				.perform( delete( "/admin/cake/" + cakeIdToBeDeleted )
						.accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isNoContent() )
				.andExpect( jsonPath( "$" ).doesNotExist() );

		verify( cakeRepository, times( 1 ) ).deleteById( cakeIdToBeDeleted );
	}

	public static String asJsonString( final Object obj ) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString( obj );
	}

}

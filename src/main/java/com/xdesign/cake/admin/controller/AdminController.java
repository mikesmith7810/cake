package com.xdesign.cake.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.cake.domain.Cake;
import com.xdesign.cake.domain.CakeTray;

@RestController
public class AdminController {
	@GetMapping("/admin/cakes")
	public CakeTray getCakes() {
		final List<Cake> cakes = new ArrayList<Cake>();
		cakes.add( Cake.builder().id( 1 ).name( "Chocolate" ).build() );

		return CakeTray.builder().cakes( cakes ).build();
	}

	@PostMapping("/admin/cake")
	public CakeTray addCake( @RequestBody final Cake cake ) {
		//add a cake here properly
		final List<Cake> cakes = new ArrayList<Cake>();
		cakes.add( Cake.builder().id( 1 ).name( "Chocolate" ).build() );
		cakes.add( Cake.builder().id( 2 ).name( "Cream" ).build() );

		return CakeTray.builder().cakes( cakes ).build();
	}

	@PutMapping("/admin/cake")
	public CakeTray updateCake( @RequestBody final Cake cake ) {
		//update a cake here properly
		final List<Cake> cakes = new ArrayList<Cake>();
		cakes.add( Cake.builder().id( 1 ).name( "Chocolate" ).build() );
		cakes.add( Cake.builder().id( 2 ).name( "Strawberry" ).build() );

		return CakeTray.builder().cakes( cakes ).build();
	}

	@DeleteMapping("/admin/cake/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "OK")
	public Optional deleteCake( @PathVariable("id") final int id ) {
		//delete a cake here properly

		return Optional.empty();
	}
}

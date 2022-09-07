package com.xdesign.cake.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.cake.domain.Cake;
import com.xdesign.cake.domain.CakeTray;

@RestController
public class AdminController {
	@GetMapping("/admin/cakes")
	public CakeTray getCakes() {

		List<Cake> cakes = new ArrayList<Cake>();
		cakes.add( Cake.builder().id( 1 ).name( "Chocolate" ).build() );

		return CakeTray.builder().cakes( cakes ).build();

	}
}

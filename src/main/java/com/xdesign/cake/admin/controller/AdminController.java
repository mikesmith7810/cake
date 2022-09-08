package com.xdesign.cake.admin.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.cake.db.repository.CakeRepository;
import com.xdesign.cake.domain.Cake;
import com.xdesign.cake.domain.CakeTray;
import com.xdesign.cake.helper.RandomWordRetriever;

@RestController
public class AdminController {

	@Autowired
	private CakeRepository cakeRepository;

	@Autowired
	private RandomWordRetriever randomWordRetriever;

	@GetMapping("/admin/cakes")
	public CakeTray getCakes() {
		return CakeTray.builder().cakes( cakeRepository.findAll() ).build();
	}

	@PostMapping("/admin/cake")
	public CakeTray addCake( @RequestBody final Cake cake ) {
		cakeRepository.save( cake );

		return CakeTray.builder().cakes( cakeRepository.findAll() ).build();
	}

	@DeleteMapping("/admin/cake/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "OK")
	public Optional deleteCake( @PathVariable("id") final int id ) {
		cakeRepository.deleteById( id );

		return Optional.empty();
	}

	@PostMapping("/admin/cake/magiccreate/{numberOfCakes}")
	public CakeTray addCake( @PathVariable("numberOfCakes") final int numberOfCakes )
			throws IOException {

		//create a strema here which selects a random set of words from cvs file
		//then for each one, save a cake
		randomWordRetriever.getRandomWords( numberOfCakes )
				.stream()
				.forEach( randomWord -> cakeRepository
						.save( Cake.builder().name( randomWord ).build() ) );

		return CakeTray.builder().cakes( cakeRepository.findAll() ).build();
	}
}

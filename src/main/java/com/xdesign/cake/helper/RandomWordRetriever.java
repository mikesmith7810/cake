package com.xdesign.cake.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

@Component
public class RandomWordRetriever {
	public List<String> getRandomWords( final int numberOfCakes ) throws IOException {

		final List<String> words = Files
				.lines( Paths.get( "src/main/resources/words/randomwords.txt" ) )
				.collect( Collectors.toList() );

		final List<String> randomWords = new ArrayList<>();

		IntStream.range( 0, numberOfCakes )
				.forEach( i -> randomWords.add(
						words.get( ThreadLocalRandom.current().nextInt( 1, words.size() ) ) ) );

		return randomWords;
	}
}

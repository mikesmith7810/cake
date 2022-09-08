package com.xdesign.cake.helper;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RandomWordRetrieverTest {

	RandomWordRetriever randomWordRetriever;

	@BeforeAll
	void setup() {
		randomWordRetriever = new RandomWordRetriever();
	}

	@Test
	void shouldRetrieveRandomWordsFromResourcesFile() throws IOException {

		final List<String> randomWords = randomWordRetriever.getRandomWords( 3 );

		assertThat( randomWords ).size().isEqualTo( 3 );
		assertThat( randomWords.stream().distinct().collect( Collectors.toList() ).size() )
				.isEqualTo( 3 );
	}

}

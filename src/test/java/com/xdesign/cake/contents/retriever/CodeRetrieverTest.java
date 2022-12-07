package com.xdesign.cake.contents.retriever;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CodeRetrieverTest {

	private CodeRetriever codeRetriever;

	@BeforeEach
	public void setup() {
		codeRetriever = new CodeRetriever();
	}

	@Test
	public void shouldUseCallGitHubForCode() throws IOException {
		final String code = codeRetriever.retrieveCodeFor("com/test/Test.java");

		assertThat( code ).isNotNull();
	}
}

package com.xdesign.cake.contents;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xdesign.cake.contents.retriever.CodeRetriever;
import com.xdesign.cake.domain.Example;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ContentsRetrieverTest {

	private ContentsRetriever contentsRetriever;

	@Mock
	private CodeRetriever codeRetriever;
	@Mock
	private ContentsStore contentsStore;

	@BeforeEach
	public void setup() {
		contentsRetriever = new ContentsRetriever( codeRetriever, contentsStore );
		contentsRetriever.setExamples( List.of( Example.builder()
				.githubLocation( "com/test/Test.java" )
				.chapter( "test chapter" )
				.githubLocation( "com/test/Test.java" )
				.build() ) );
	}

	@Test
	public void shouldUseCodeRetrieverToGetSource() throws IOException {

		when( contentsStore.getContents() ).thenReturn( null );

		contentsRetriever.cacheContents();

		verify( codeRetriever ).retrieveCodeFor( "com/test/Test.java" );
	}
}

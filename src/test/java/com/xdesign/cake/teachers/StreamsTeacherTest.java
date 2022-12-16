package com.xdesign.cake.teachers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;
import com.xdesign.cake.contents.ContentsStore;
import com.xdesign.cake.demonstrators.streams.ForEachDemonstrator;
import com.xdesign.cake.domain.Chapter;
import com.xdesign.cake.domain.Contents;
import com.xdesign.cake.domain.Example;
import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;
import com.xdesign.cake.task.TaskType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class StreamsTeacherTest {

	StreamsTeacher streamsTeacher;

	@Mock
	ForEachDemonstrator forEachDemonstrator;

	@Mock
	ContentsStore contentsStore;

	@BeforeEach
	public void setup() {
		this.streamsTeacher = new StreamsTeacher( forEachDemonstrator, contentsStore );
	}

	@Test
	public void shouldCallDemonstrator() {
		when( forEachDemonstrator
				.runExampleFor( ImmutableList.of( "iamatest", "andiamatestaswell" ) ) )
						.thenReturn( "iamatest//nandiamatestaswell" );

		final String result = streamsTeacher.runTaskBasedUponType( TaskType.FOREACH,
				ImmutableList.of( "iamatest", "andiamatestaswell" ) );

		verify( forEachDemonstrator )
				.runExampleFor( ImmutableList.of( "iamatest", "andiamatestaswell" ) );
	}

	@Test
	public void shouldHaveAResultWithAllFields() {
		when( forEachDemonstrator.runExampleFor( ImmutableList.of( "word1", "word2" ) ) )
				.thenReturn( "OUTPUT TO CONSOLE" );
		when( contentsStore.retrieveContents() ).thenReturn( Contents.builder()
				.chapters( List.of( Chapter.builder()
						.examples( List.of( Example.builder()
								.taskType( TaskType.FOREACH )
								.sourceCode( "some code" )
								.description( "for each to loop" )
								.build() ) )
						.build() ) )
				.build() );

		final TaskResult result = streamsTeacher.teachThis( Task.builder()
				.taskType( TaskType.FOREACH )
				.parameters( List.of( "word1", "word2" ) )
				.build() );

		assertThat( result.getValue() ).isEqualTo( "OUTPUT TO CONSOLE" );
		assertThat( result.getType() ).isEqualTo( TaskType.FOREACH );
		assertThat( result.getDescription() ).isEqualTo( "for each to loop" );
		assertThat( result.getSourceCode() ).isEqualTo( "some code" );
	}

}

package com.xdesign.cake.helper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import static com.xdesign.cake.helper.MessageComposer.CODEBLOCK;
import static com.xdesign.cake.helper.MessageComposer.NEWLINE;
import static com.xdesign.cake.helper.MessageComposer.TAB;
import static com.xdesign.cake.helper.MessageComposer.bold;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xdesign.cake.contents.ContentsStore;
import com.xdesign.cake.domain.Chapter;
import com.xdesign.cake.domain.Contents;
import com.xdesign.cake.domain.Example;
import com.xdesign.cake.task.TaskResult;
import com.xdesign.cake.task.TaskType;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageComposerTest {

	@Mock
	private ContentsStore contentsStore;

	private TaskResult taskResult;

	private MessageComposer messageComposer;

	@BeforeEach
	public void setup() {
		this.messageComposer = new MessageComposer( contentsStore );
		this.taskResult = createTestTaskResult();

	}

	@Test
	public void shouldCreateMessageFromContents() {
		when( contentsStore.retrieveContents() ).thenReturn( createTestContents() );

		final String message = messageComposer.createMessageForContents();

		assertThat( message ).isEqualTo( bold( "Test Chapter 1" ) + NEWLINE + TAB + bold(
				"Red 1 Example" ) + NEWLINE + TAB + TAB + "This is a red test piece of code" + NEWLINE + TAB + TAB + "rest endpoint : " + CODEBLOCK + "/test/api1" + CODEBLOCK + NEWLINE + TAB + TAB + "slash command example : " + CODEBLOCK + "/slashtest1" + CODEBLOCK + NEWLINE + NEWLINE + TAB + bold(
						"Green 2 Example" ) + NEWLINE + TAB + TAB + "This is a green test piece of code" + NEWLINE + TAB + TAB + "rest endpoint : " + CODEBLOCK + "/test/api2" + CODEBLOCK + NEWLINE + TAB + TAB + "slash command example : " + CODEBLOCK + "/slashtest2" + CODEBLOCK + NEWLINE + NEWLINE +

				bold( "Test Chapter 2" ) + NEWLINE + TAB + bold(
						"Blue 1 Example" ) + NEWLINE + TAB + TAB + "This is a blue test piece of code" + NEWLINE + TAB + TAB + "rest endpoint : " + CODEBLOCK + "/test/api3" + CODEBLOCK + NEWLINE + TAB + TAB + "slash command example : " + CODEBLOCK + "/slashtest3" + CODEBLOCK + NEWLINE + NEWLINE + TAB + bold(
								"Yellow 2 Example" ) + NEWLINE + TAB + TAB + "This is a yellow test piece of code" + NEWLINE + TAB + TAB + "rest endpoint : " + CODEBLOCK + "/test/api4" + CODEBLOCK + NEWLINE + TAB + TAB + "slash command example : " + CODEBLOCK + "/slashtest4" + CODEBLOCK + NEWLINE + NEWLINE );
	}

	@Test
	public void shouldCreateMessageForLearning() {
		final String message = messageComposer.createMessageForTaskResult( taskResult );

		assertThat( message ).isEqualTo( bold( "Result" ) + NEWLINE + "Result" + NEWLINE + bold(
				"Source Code : " ) + NEWLINE + CODEBLOCK + "Foreach source code" + CODEBLOCK );

	}

	private TaskResult createTestTaskResult() {
		return TaskResult.builder()
				.type( TaskType.FOREACH )
				.value( "Result" )
				.sourceCode( "Foreach source code" )
				.build();
	}

	private Contents createTestContents() {
		return Contents.builder()
				.chapters( List.of(
						Chapter.builder()
								.name( "Test Chapter 1" )
								.examples( List.of(
										Example.builder()
												.name( "Red 1 Example" )
												.description( "This is a red test piece of code" )
												.slashCommand( "/slashtest1" )
												.apiCall( "/test/api1" )
												.sourceCode( "Foreach source code" )
												.taskType( TaskType.FOREACH )
												.build(),
										Example.builder()
												.name( "Green 2 Example" )
												.description( "This is a green test piece of code" )
												.slashCommand( "/slashtest2" )
												.apiCall( "/test/api2" )
												.sourceCode( "Predicate source code" )
												.taskType( TaskType.PREDICATE )
												.build() ) )
								.build(),
						Chapter.builder()
								.name( "Test Chapter 2" )
								.examples( List.of(
										Example.builder()
												.name( "Blue 1 Example" )
												.description( "This is a blue test piece of code" )
												.slashCommand( "/slashtest3" )
												.apiCall( "/test/api3" )
												.taskType( TaskType.SUPPLIER )
												.sourceCode( "Supplier source code" )
												.build(),
										Example.builder()
												.name( "Yellow 2 Example" )
												.slashCommand( "/slashtest4" )
												.apiCall( "/test/api4" )
												.description(
														"This is a yellow test piece of code" )
												.taskType( TaskType.CONSUMER )
												.sourceCode( "Consumer source code" )
												.build() ) )
								.build() ) )
				.build();
	}
}

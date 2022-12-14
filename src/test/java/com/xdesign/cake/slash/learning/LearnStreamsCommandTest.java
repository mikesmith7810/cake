package com.xdesign.cake.slash.learning;

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

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.cake.controller.StreamsController;
import com.xdesign.cake.helper.MessageComposer;
import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;
import com.xdesign.cake.task.TaskType;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LearnStreamsCommandTest {

	private LearnStreamsCommand learnStreamsCommand;

	@Mock
	private StreamsController streamsController;

	@Mock
	private MessageComposer messageComposer;

	@Mock
	private SlashCommandRequest slashCommandRequest;

	@Mock
	private SlashCommandContext slashCommandContext;

	private Task streamsTask;

	private TaskResult taskResult;

	@BeforeEach
	public void setup() {

		this.learnStreamsCommand = new LearnStreamsCommand( streamsController, messageComposer );

		this.streamsTask = Task.builder()
				.taskType( TaskType.valueOf( "FOREACH" ) )
				.parameters( List.of( "word1", "word2" ) )
				.build();

		this.taskResult = TaskResult.builder()
				.type( TaskType.FOREACH )
				.value( "word1 word2" )
				.sourceCode( "Some source code" )
				.build();
	}

	@Test
	public void shouldCallStreamsController() {

		final Response response = learnStreamsCommand.doRespond( "FOREACH word1 word2",
				slashCommandRequest, slashCommandContext );

		verify( streamsController ).runLearningMaterial( streamsTask );
	}

	@Test
	public void shouldHaveAResponseWithAMessageInIt() {

		when( streamsController.runLearningMaterial( streamsTask ) )
				.thenReturn( taskResult );

		when( slashCommandContext.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( messageComposer.createMessageForTaskResult( taskResult ) )
				.build() ) ).thenReturn( Response.builder().body( "blah" ).build() );

		final Response response = learnStreamsCommand.doRespond( "FOREACH word1 word2",
				slashCommandRequest, slashCommandContext );

		assertThat( response.getBody() ).isEqualTo( "blah" );
	}
}

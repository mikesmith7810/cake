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
import com.xdesign.cake.controller.ContentsController;
import com.xdesign.cake.domain.Chapter;
import com.xdesign.cake.domain.Contents;
import com.xdesign.cake.domain.Example;
import com.xdesign.cake.helper.MessageComposer;
import com.xdesign.cake.task.TaskType;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContentsCommandTest {

	private ContentsCommand contentsCommand;

	@Mock
	private ContentsController contentsController;

	@Mock
	private SlashCommandRequest slashCommandRequest;

	@Mock
	private SlashCommandContext slashCommandContext;

	private Contents contents;

	@BeforeEach
	public void setup() {
		contentsCommand = new ContentsCommand( contentsController );

		this.contents = createTestContents();

		when( contentsController.getContents() ).thenReturn( contents );
		when( slashCommandContext.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( MessageComposer.createMessageFrom( contents ) )
				.build() ) ).thenReturn( Response.builder().body( "blah" ).build() );
	}

	@Test
	public void shouldGetContentsFromController() {
		final Response response = contentsCommand.doRespond( "Test Message", slashCommandRequest,
				slashCommandContext );

		verify( contentsController ).getContents();
	}

	@Test
	public void shouldReturnResponseWithContents() {

		final Response response = contentsCommand.doRespond( "Test Message", slashCommandRequest,
				slashCommandContext );

		assertThat( response.getBody() ).isEqualTo( "blah" );
	}

	private Contents createTestContents() {
		return Contents.builder()
				.chapters( List.of( Chapter.builder()
						.name( "Test Chapter" )
						.examples( List.of( Example.builder()
								.name( "Test Example" )
								.sourceCode( "public void shouldTest(){//somecode};" )
								.taskType( TaskType.FOREACH )
								.build() ) )
						.build() ) )
				.build();
	}
}

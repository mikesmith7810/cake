package com.xdesign.cake.slash.learning;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.slack.api.Slack;
import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.xdesign.cake.controller.StreamsController;
import com.xdesign.cake.slash.MessageExtractingCommand;
import com.xdesign.cake.slash.annotations.SlashCommand;
import com.xdesign.cake.task.StreamsTask;
import com.xdesign.cake.task.StreamsTaskResult;
import com.xdesign.cake.task.TaskType;

import lombok.extern.slf4j.Slf4j;

@SlashCommand("learnjavastreams")
@Slf4j
@Component
public class LearnStreamsCommand extends MessageExtractingCommand {

	private final StreamsController streamsController;

	public LearnStreamsCommand( final StreamsController streamsController ) {
		this.streamsController = streamsController;
	}

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) throws SlackApiException, IOException {

		final String responseMessage = "testjavalearn";

		Slack slack = Slack.getInstance();
		MethodsClient methods = slack.methods( System.getenv( "SLACK_OAUTH_TOKEN" ) );

		StreamsTaskResult result = streamsController.runLearningMaterial( StreamsTask.builder()
				.taskType( TaskType.FOREACH )
				.parameters( List.of( "Mike", "is", "cool" ) )
				.build() );

		return context.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( result.getValue() + result.getType() )
				.build() );
	}
}

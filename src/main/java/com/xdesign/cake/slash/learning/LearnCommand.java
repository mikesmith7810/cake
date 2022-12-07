package com.xdesign.cake.slash.learning;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.slack.api.Slack;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.xdesign.cake.controller.StreamsController;
import com.xdesign.cake.slash.MessageExtractingCommand;
import com.xdesign.cake.slash.annotations.SlashCommand;
import com.xdesign.cake.task.StreamsTask;
import com.xdesign.cake.task.StreamsTaskResult;
import com.xdesign.cake.task.StreamsType;

import lombok.extern.slf4j.Slf4j;

@SlashCommand("learnjava")
@Slf4j
@Component
public class LearnCommand extends MessageExtractingCommand {

	private final StreamsController streamsController;

	public LearnCommand(final StreamsController streamsController){
		this.streamsController = streamsController;
	}

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) throws SlackApiException, IOException {

		final String responseMessage = "testjavalearn";

		Slack slack = Slack.getInstance();
		MethodsClient methods = slack.methods( System.getenv( "SLACK_OAUTH_TOKEN" ) );

		StreamsTaskResult result = streamsController.runLearningMaterial(StreamsTask.builder().taskType(StreamsType.FOREACH).parameters(List.of("Mike","is","cool")).build());

		ChatPostMessageResponse response = methods.chatPostMessage( ChatPostMessageRequest.builder()
				.channel( "#caketest" )
				.text(result.getValue() )
				.build() );

		return context.ack( res -> res.responseType( "in_channel" ).text( result.getValue()+ result.getType()  ) );
	}
}

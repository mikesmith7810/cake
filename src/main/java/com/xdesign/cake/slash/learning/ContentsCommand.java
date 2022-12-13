package com.xdesign.cake.slash.learning;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.slack.api.Slack;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.xdesign.cake.controller.ContentsController;
import com.xdesign.cake.domain.Contents;
import com.xdesign.cake.slash.MessageExtractingCommand;
import com.xdesign.cake.slash.annotations.SlashCommand;

import lombok.extern.slf4j.Slf4j;

@SlashCommand("contents")
@Slf4j
@Component
public class ContentsCommand extends MessageExtractingCommand {

	public static final String NEWLINE = "\n";
	private final ContentsController contentsController;

	public ContentsCommand( final ContentsController contentsController ) {
		this.contentsController = contentsController;
	}

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) throws SlackApiException, IOException {

		final Slack slack = Slack.getInstance();
		final MethodsClient methods = slack.methods( System.getenv( "SLACK_OAUTH_TOKEN" ) );

		Contents contents = contentsController.getContents();

		//ChatPostMessageResponse response = methods.chatPostMessage( ChatPostMessageRequest.builder()
		//				.channel( "#caketest" )
		//				.text( "`" + "chat respsone test" + "`" )
		//				.mrkdwn( true )
		//				.build() );

		return context.ack( res -> res.responseType( "in_channel" )
				.text( "*" + contents.getChapters()
						.get( 0 )
						.getName() + "*" + NEWLINE + "```" + contents.getChapters()
								.get( 0 )
								.getExamples()
								.get( 0 )
								.getSourceCode() + "```" ) );

	}
}

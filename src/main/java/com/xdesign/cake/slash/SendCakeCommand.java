package com.xdesign.cake.slash;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.util.StringUtils;

import com.slack.api.Slack;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.xdesign.cake.db.repository.CakeRepository;
import com.xdesign.cake.domain.Cake;
import com.xdesign.cake.slash.annotations.SlashCommand;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SlashCommand("sendrandomcake")
@AllArgsConstructor
@Slf4j
public class SendCakeCommand extends MessageExtractingCommand {

	CakeRepository cakeRepository;

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) throws SlackApiException, IOException {

		log.info( "Message : " + message );
		log.info( "Request : " + request.getRequestBodyAsString() );
		log.info( "Channel :" + context.getChannelId() );

		final List<Cake> availableCakes = cakeRepository.findAll();
		final Random random = new Random();
		final String cakeMessage = "Have a random '" + StringUtils
				.capitalize( availableCakes.get( random.nextInt( availableCakes.size() ) )
						.getName() ) + "' Cake!! :tada:";

		Slack slack = Slack.getInstance();
		MethodsClient methods = slack
				.methods( "xoxb-2213511230-4096670683063-Q3THYqe54EqLXpCWx4j3jWYE" );

		log.info( "Message posting " );
		// Build a request object
		ChatPostMessageRequest apiRequest = ChatPostMessageRequest.builder()
				//.channel( "D043YU7KEHW" ) // Use a channel ID `C1234567` is preferable
				.channel( "#fanduel-teinders" ) // Use a channel ID `C1234567` is preferable
				.text( cakeMessage )
				.build();

		log.info( "Message posted " );

		// Get a response as a Java object
		ChatPostMessageResponse response = methods.chatPostMessage( apiRequest );

		return context.ack( res -> res.responseType( "in_channel" ).text( cakeMessage ) );
	}
}

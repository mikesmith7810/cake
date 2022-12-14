package com.xdesign.cake.slash.learning;

import org.springframework.stereotype.Component;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.cake.controller.ContentsController;
import com.xdesign.cake.domain.Contents;
import com.xdesign.cake.helper.MessageComposer;
import com.xdesign.cake.slash.MessageExtractingCommand;
import com.xdesign.cake.slash.annotations.SlashCommand;

import lombok.extern.slf4j.Slf4j;

@SlashCommand("contents")
@Slf4j
@Component
public class ContentsCommand extends MessageExtractingCommand {

	private final ContentsController contentsController;

	public ContentsCommand( final ContentsController contentsController ) {
		this.contentsController = contentsController;
	}

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) {

		final Contents contents = contentsController.getContents();
		//
		//		Response response = context.ack( SlashCommandResponse.builder()
		//				.responseType( "in_channel" )
		//				.text( "blah" )
		//				.build() );
		Response response = context.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( MessageComposer.createMessageFrom( contents ) )
				.build() );

		return response;

	}
}

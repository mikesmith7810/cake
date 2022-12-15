package com.xdesign.cake.slash.learning;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.cake.controller.FunctionalInterfacesController;
import com.xdesign.cake.helper.MessageComposer;
import com.xdesign.cake.slash.MessageExtractingCommand;
import com.xdesign.cake.slash.annotations.SlashCommand;
import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;
import com.xdesign.cake.task.TaskType;

import lombok.extern.slf4j.Slf4j;

@SlashCommand("learnjava/functionalinterfaces")
@Slf4j
@Component
public class LearnFunctionalInterfacesCommand extends MessageExtractingCommand {

	private final FunctionalInterfacesController functionalInterfacesController;

	private final MessageComposer messageComposer;

	public LearnFunctionalInterfacesCommand(final FunctionalInterfacesController functionalInterfacesController,
                                            final MessageComposer messageComposer ) {
		this.functionalInterfacesController = functionalInterfacesController;
		this.messageComposer = messageComposer;
	}

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) {

		final String[] arguments = message.split( " " );

		final TaskResult result = functionalInterfacesController.runLearningMaterial( Task.builder()
				.taskType( TaskType.valueOf( arguments[0] ) )
				.parameters( Arrays.asList( arguments ).subList( 1, arguments.length ) )
				.build() );

		return context.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( messageComposer.createMessageForTaskResult( result ) )
				.build() );
	}
}

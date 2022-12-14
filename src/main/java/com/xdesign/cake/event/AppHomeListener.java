package com.xdesign.cake.event;

import static com.slack.api.model.block.Blocks.asBlocks;
import static com.slack.api.model.block.Blocks.divider;
import static com.slack.api.model.block.Blocks.header;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.view.Views.view;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.slack.api.app_backend.events.payload.EventsApiPayload;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.bolt.handler.BoltEventHandler;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.event.AppHomeOpenedEvent;
import com.slack.api.model.view.View;

import lombok.Data;

@Component
@Data
public class AppHomeListener implements BoltEventHandler<AppHomeOpenedEvent> {

	@Override
	public Response apply( final EventsApiPayload<AppHomeOpenedEvent> event,
			final EventContext context ) throws IOException, SlackApiException {

		final View theView = existingUserView( event );

		context.client()
				.viewsPublish( req -> req.userId( event.getEvent().getUser() ).view( theView ) );

		return context.ack();
	}


	private View existingUserView( final EventsApiPayload<AppHomeOpenedEvent> event ) {
		return view( view -> view.type( "home" )
				.blocks( asBlocks( header( h -> h.text( plainText( "Welcome!" ) ) ),
						section( section -> section.text( markdownText( mt -> mt.text(
								"Hi there, " + " I'm your friendly app for brushing up on your Java skills :robot_face:.\n" ) ) ) ),
						divider(), section( section -> section.text( markdownText(
								mt -> mt.text(
										"Hit slash command */contents* for a list of resources.\n" + "Happy Learning :tada:!" ) ) ) ) ) ) );
	}
}

package com.xdesign.cake.slash;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.methods.SlackApiException;
import com.xdesign.cake.db.repository.CakeRepository;
import com.xdesign.cake.domain.Cake;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SendCakeCommandTest {


    private SendCakeCommand sendCakeCommand;

    @Mock
    SlashCommandRequest slashCommandRequest;
    @Mock
    SlashCommandContext slashCommandContext;
    @Mock
    CakeRepository cakeRepository;

    public static final String MESSAGE = "MESSAGE";

    @BeforeEach
	void setup() {
		sendCakeCommand = new SendCakeCommand(cakeRepository);
	}

    @Test
    public void shouldRetrieveAllAvailableCakes() throws SlackApiException, IOException {
        when(cakeRepository.findAll()).thenReturn(ImmutableList.of(Cake.builder().build(),Cake.builder().build()));

        sendCakeCommand.doRespond(MESSAGE, slashCommandRequest,slashCommandContext);

        verify(cakeRepository).findAll();
    }

}

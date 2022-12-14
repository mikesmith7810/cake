package com.xdesign.cake.helper;

import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.ContentsStore;
import com.xdesign.cake.domain.Chapter;
import com.xdesign.cake.task.StreamsTaskResult;

@Component
public class MessageComposer {
	public static final String NEWLINE = "\n";
	public static final String TAB = "\t";
	public static final String BOLD = "*";
	public static final String ITALIC = "_";
	public static final String CODEBLOCK = "```";

	private ContentsStore contentStore;

	public MessageComposer( final ContentsStore contentsStore ) {
		this.contentStore = contentsStore;
	}

	public String createMessageForContents() {

		return contentStore.retrieveContents()
				.getChapters()
				.stream()
				.map( createChapterMessage() )
				.collect( StringBuilder::new, StringBuilder::append, StringBuilder::append )
				.toString();
	}

	public String createMessageForTaskResult( final StreamsTaskResult streamsTaskResult ) {

		return bold( "Result" ) + NEWLINE + streamsTaskResult.getValue() + NEWLINE + bold(
				"Source Code : " ) + NEWLINE + CODEBLOCK + streamsTaskResult
						.getSourceCode() + CODEBLOCK;
	}

	@NotNull
	private static Function<Chapter, String> createChapterMessage() {
		return chapter -> bold( chapter.getName() ) + NEWLINE + chapter.getExamples()
				.stream()
				.map( example -> TAB + bold( example.getName() ) + NEWLINE + TAB + TAB + example
						.getDescription() + NEWLINE + TAB + TAB + "rest endpoint : " + CODEBLOCK + example
								.getApiCall() + CODEBLOCK + NEWLINE + TAB + TAB + "slash command example : " + CODEBLOCK + example
										.getSlashCommand() + CODEBLOCK + NEWLINE + NEWLINE )
				.collect( StringBuilder::new, StringBuilder::append, StringBuilder::append );
	}

	public static String bold( final String test ) {
		return BOLD + test + BOLD;
	}

}
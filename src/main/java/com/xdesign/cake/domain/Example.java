package com.xdesign.cake.domain;

import com.xdesign.cake.task.TaskType;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Example {
	private final String name;
	private final String description;
	private final String apiCall;
	private final String githubLocation;
	private final String chapter;
	private String sourceCode;
	private final TaskType taskType;
	private final String slashCommand;
	private final String slashParameters;
}

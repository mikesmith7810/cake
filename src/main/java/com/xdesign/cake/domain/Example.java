package com.xdesign.cake.domain;

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
}

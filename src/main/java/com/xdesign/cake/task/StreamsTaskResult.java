package com.xdesign.cake.task;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StreamsTaskResult {
	final private StreamsType type;
	final private String value;

}
